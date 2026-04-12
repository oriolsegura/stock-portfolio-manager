package com.oriolsegura.opulentia.request.stock;

import com.oriolsegura.opulentia.validation.currency.ValidCurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record CreateStockRequest(
		@NotBlank
		@Size(min = 3, max = 255)
		String companyName,
		@NotBlank
		@Size(min = 1, max = 7)
		String ticker,
		@NotBlank
		@Size(min = 3, max = 3)
		@ValidCurrencyCode
		String currencyCode
) {

	//

}
