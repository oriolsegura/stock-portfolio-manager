package com.oriolsegura.opulentia.service;

import com.oriolsegura.opulentia.provider.MarketDataProvider;
import com.oriolsegura.opulentia.request.stock.CreateStockRequest;
import com.oriolsegura.opulentia.exception.stock.StockAlreadyExistsException;
import com.oriolsegura.opulentia.mapper.StockMapper;
import com.oriolsegura.opulentia.model.Stock;
import com.oriolsegura.opulentia.repository.StockRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class StockService {

	private final StockRepository repository;

	private final StockMapper mapper;

	private final MarketDataProvider marketDataProvider;

	public StockService(
			StockRepository repository,
			StockMapper mapper,
			@Qualifier("RandomProvider") MarketDataProvider marketDataProvider
	) {
		this.repository = repository;
		this.mapper = mapper;
		this.marketDataProvider = marketDataProvider;
	}

	public Stock createStock(CreateStockRequest request) throws StockAlreadyExistsException {
		try {
			return repository.save(mapper.fromCreateRequest(request));
		} catch (DataIntegrityViolationException e) {
			throw new StockAlreadyExistsException();
		}
	}

	public List<Stock> getAllStocks() {
		return repository.findAll();
	}

	@Transactional
	public void updatePrices() {
		List<Stock> stocks = getAllStocks();

		HashMap<Long, BigDecimal> prices = marketDataProvider.getCurrentPrice(stocks);

		for (Stock stock : stocks) {
			BigDecimal price = prices.get(stock.getId());

			if (price == null) {
				continue;
			}

			stock.setLastPrice(price);
		}
	}

}
