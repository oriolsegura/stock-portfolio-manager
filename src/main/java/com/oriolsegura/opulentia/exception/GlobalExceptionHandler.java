package com.oriolsegura.opulentia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserExists() {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@ExceptionHandler(WrongCredentialsException.class)
	public ResponseEntity<?> handleWrongCredentials() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
