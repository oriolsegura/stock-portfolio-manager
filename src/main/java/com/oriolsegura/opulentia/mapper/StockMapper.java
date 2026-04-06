package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.dto.stock.CreateStockDto;
import com.oriolsegura.opulentia.dto.stock.StockDto;
import com.oriolsegura.opulentia.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

	public StockDto toDto(Stock stock) {
		return new StockDto(stock.getId(), stock.getCompanyName(), stock.getTicker(), stock.getCurrencyCode());
	}

	public Stock fromCreateRequest(CreateStockDto data) {
		Stock stock = new Stock();
		stock.setCompanyName(data.companyName());
		stock.setTicker(data.ticker());
		stock.setCurrencyCode(data.currencyCode().toUpperCase());

		return stock;
	}

}
