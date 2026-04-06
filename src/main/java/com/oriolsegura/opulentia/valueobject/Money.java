package com.oriolsegura.opulentia.valueobject;

import java.math.BigDecimal;
import java.util.Currency;

public final class Money {

	private final BigDecimal amount;

	private final Currency currency;

	private Money(BigDecimal amount, Currency currency) {
		if (amount == null || currency == null) {
			throw new IllegalArgumentException("Amount and currency must not be null");
		}

		this.amount = amount;
		this.currency = currency;
	}

	public static Money of(BigDecimal amount, String currencyCode) {
		return new Money(amount, Currency.getInstance(currencyCode));
	}

	public static Money of(BigDecimal amount, Currency currency) {
		return new Money(amount, currency);
	}

	public BigDecimal amount() {
		return amount;
	}

	public Currency currency() {
		return currency;
	}

	private void requireSameCurrency(Money other) {
		if (! this.currency.equals(other.currency)) {
			throw new IllegalArgumentException("Currency mismatch");
		}
	}

}
