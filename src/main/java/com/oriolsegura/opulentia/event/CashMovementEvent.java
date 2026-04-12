package com.oriolsegura.opulentia.event;

import java.math.BigDecimal;

public record CashMovementEvent(
		Long accountId,
		BigDecimal amount,
		String description
) {

	//

}
