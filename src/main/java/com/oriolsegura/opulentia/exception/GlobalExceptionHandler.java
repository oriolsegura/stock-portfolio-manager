package com.oriolsegura.opulentia.exception;

import com.oriolsegura.opulentia.exception.account.AccountNameAlreadyInUseException;
import com.oriolsegura.opulentia.exception.account.AccountNotFoundException;
import com.oriolsegura.opulentia.exception.account.AccountNotOwnedException;
import com.oriolsegura.opulentia.exception.auth.UserAlreadyExistsException;
import com.oriolsegura.opulentia.exception.auth.WrongCredentialsException;
import com.oriolsegura.opulentia.exception.stock.StockAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Auth

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserExists() {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@ExceptionHandler(WrongCredentialsException.class)
	public ResponseEntity<?> handleWrongCredentials() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	// Stock

	@ExceptionHandler(StockAlreadyExistsException.class)
	public ResponseEntity<?> handleStockAlreadyExists() {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	// Account

	@ExceptionHandler(AccountNameAlreadyInUseException.class)
	public ResponseEntity<?> handleAccountNameAlreadyInUse() {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> handleAccountNotFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler(AccountNotOwnedException.class)
	public ResponseEntity<?> handleAccountNotOwned() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
