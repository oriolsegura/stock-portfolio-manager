package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.request.stock.CreateStockRequest;
import com.oriolsegura.opulentia.dto.stock.StockDto;
import com.oriolsegura.opulentia.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

	public StockDto toDto(Stock stock) {
		return new StockDto(
				stock.getId(),
				stock.getCompanyName(),
				stock.getTicker(),
				stock.getCurrencyCode(),
				stock.getPrice(),
				stock.getPriceUpdatedAt()
		);
	}

	public Stock fromCreateRequest(CreateStockRequest request) {
		Stock stock = new Stock();
		stock.setCompanyName(request.companyName());
		stock.setTicker(request.ticker());
		stock.setCurrencyCode(request.currencyCode().toUpperCase());

		return stock;
	}

}
