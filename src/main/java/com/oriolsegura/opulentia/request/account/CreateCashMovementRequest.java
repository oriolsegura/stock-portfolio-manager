package com.oriolsegura.opulentia.request.account;

import com.oriolsegura.opulentia.validation.amount.NonZero;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;

@NullMarked
public record CreateCashMovementRequest(
		@NonZero BigDecimal amount,
		String description
) {

	//

}
