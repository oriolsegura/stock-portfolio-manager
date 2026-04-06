package com.oriolsegura.opulentia.dto.account;

import java.math.BigDecimal;

public record AccountDto(Long id, String name, BigDecimal balance, String currencyCode) {

	//

}
