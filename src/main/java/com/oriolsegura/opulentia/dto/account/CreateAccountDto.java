package com.oriolsegura.opulentia.dto.account;

import com.oriolsegura.opulentia.validation.currency.ValidCurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record CreateAccountDto(
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
