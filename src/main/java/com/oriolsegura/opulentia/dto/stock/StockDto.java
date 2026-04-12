package com.oriolsegura.opulentia.dto.stock;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NullMarked
public record StockDto(
		Long id,
		String companyName,
		String ticker,
		String currencyCode,
		@Nullable BigDecimal price,
		@Nullable LocalDateTime priceUpdatedAt
) {

	//

}
