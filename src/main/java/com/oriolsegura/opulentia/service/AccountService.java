package com.oriolsegura.opulentia.service;

import com.oriolsegura.opulentia.dto.account.CreateAccountDto;
import com.oriolsegura.opulentia.exception.account.AccountNameAlreadyInUseException;
import com.oriolsegura.opulentia.mapper.AccountMapper;
import com.oriolsegura.opulentia.model.Account;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.repository.AccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

	private final AccountMapper mapper;

	private final AccountRepository repository;

	public AccountService(AccountMapper mapper, AccountRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public Account createAccount(User user, CreateAccountDto data) throws AccountNameAlreadyInUseException {
		Account account = mapper.fromCreateRequest(data);
		account.setUserId(user.getId());

		try {
			return repository.save(account);
		} catch (DataIntegrityViolationException e) {
			throw new AccountNameAlreadyInUseException();
		}
	}

	public List<Account> getAllUserAccounts(User user) {
		return repository.findAllByUserId(user.getId());
	}

}
