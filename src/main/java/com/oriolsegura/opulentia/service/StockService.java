package com.oriolsegura.opulentia.service;

import com.oriolsegura.opulentia.request.stock.CreateStockRequest;
import com.oriolsegura.opulentia.exception.stock.StockAlreadyExistsException;
import com.oriolsegura.opulentia.mapper.StockMapper;
import com.oriolsegura.opulentia.model.Stock;
import com.oriolsegura.opulentia.repository.StockRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

	private final StockRepository repository;

	private final StockMapper mapper;

	public StockService(StockRepository repository, StockMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
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

}
