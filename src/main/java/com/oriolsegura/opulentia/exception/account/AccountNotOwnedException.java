package com.oriolsegura.opulentia.exception.account;

public class AccountNotOwnedException extends RuntimeException {

	public AccountNotOwnedException() {
		super("You do not own this account");
	}

}
