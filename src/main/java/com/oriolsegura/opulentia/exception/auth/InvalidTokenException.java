package com.oriolsegura.opulentia.exception.auth;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

	public InvalidTokenException() {
		super("The provided token is invalid");
	}

}
