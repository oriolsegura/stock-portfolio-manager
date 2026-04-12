package com.oriolsegura.opulentia.message;

import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;

@NullMarked
public record CashMovementMessage(
		Long accountId,
		BigDecimal amount,
		String description
) {

	//

}
