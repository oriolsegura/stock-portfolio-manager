package com.oriolsegura.opulentia.dto.stock;

import com.oriolsegura.opulentia.model.Stock;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateStockDto(
		@NotNull
		@Size(min = 3, max = 255)
		String companyName,
		@NotNull
		@Size(min = 1, max = 7)
		String ticker,
		@NotNull
		@Size(min = 3, max = 3)
		String currencyCode
) {

	public Stock toEntity() {
		Stock stock = new Stock();
		stock.setCompanyName(companyName);
		stock.setTicker(ticker);
		stock.setCurrencyCode(currencyCode);

		return stock;
	}

}
