package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.dto.account.AccountDto;
import com.oriolsegura.opulentia.dto.account.CreateAccountDto;
import com.oriolsegura.opulentia.mapper.AccountMapper;
import com.oriolsegura.opulentia.model.Account;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	private final AccountMapper accountMapper;

	public AccountController(AccountService accountService, AccountMapper accountMapper) {
		this.accountService = accountService;
		this.accountMapper = accountMapper;
	}

	@PostMapping
	public ResponseEntity<AccountDto> createAccount(
			@AuthenticationPrincipal User user,
			@RequestBody @Valid CreateAccountDto data
	) {
		Account account = accountService.createAccount(user, data);

		return ResponseEntity.status(HttpStatus.CREATED).body(accountMapper.toDto(account));
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(@AuthenticationPrincipal User user) {
		List<AccountDto> accounts = accountService.getAllUserAccounts(user)
				.stream()
				.map(accountMapper::toDto)
				.toList();

		return ResponseEntity.ok(accounts);
	}

}
