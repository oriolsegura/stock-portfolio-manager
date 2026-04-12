package com.oriolsegura.opulentia.exception.auth;

import org.springframework.security.core.AuthenticationException;

public class WrongCredentialsException extends AuthenticationException {

	public WrongCredentialsException() {
		super("Wrong credentials");
	}

}
