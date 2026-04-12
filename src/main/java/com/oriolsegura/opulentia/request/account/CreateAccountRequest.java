package com.oriolsegura.opulentia.request.account;

import com.oriolsegura.opulentia.validation.currency.ValidCurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record CreateAccountRequest(
		@NotBlank
		@Size(max = 63)
		String name,
		@NotBlank
		@Size(min = 3, max = 3)
		@ValidCurrencyCode
		String currencyCode
) {

	//

}
