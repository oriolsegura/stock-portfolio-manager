package com.oriolsegura.opulentia.exception.account;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException() {
		super("You do not have enough funds to perform this operation");
	}

}
