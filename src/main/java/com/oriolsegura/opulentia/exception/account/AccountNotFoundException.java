package com.oriolsegura.opulentia.exception.account;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long id) {
		super(String.format("No account found with id %d", id));
	}

}
