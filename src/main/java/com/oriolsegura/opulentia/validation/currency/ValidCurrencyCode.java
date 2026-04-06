package com.oriolsegura.opulentia.validation.currency;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyCodeValidator.class)
public @interface ValidCurrencyCode {

	String message() default "Invalid currency code";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

}
