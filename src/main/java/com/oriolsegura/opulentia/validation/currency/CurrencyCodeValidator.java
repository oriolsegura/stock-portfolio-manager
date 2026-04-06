package com.oriolsegura.opulentia.validation.currency;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		try {
			Currency.getInstance(value.toUpperCase());
			return true;
		} catch (Throwable th) {
			return false;
		}
	}

}
