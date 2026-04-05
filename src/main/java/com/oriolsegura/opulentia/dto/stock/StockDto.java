package com.oriolsegura.opulentia.dto.stock;

import com.oriolsegura.opulentia.model.Stock;

public record StockDto(Long id, String companyName, String ticker, String currencyCode) {

	public static StockDto fromEntity(Stock stock) {
		return new StockDto(stock.getId(), stock.getCompanyName(), stock.getTicker(), stock.getCurrencyCode());
	}

}
