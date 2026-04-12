package com.oriolsegura.opulentia.validation.amount;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = NonZeroBigDecimalValidator.class)
public @interface NonZero {

	String message() default "Amount must not be zero";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
