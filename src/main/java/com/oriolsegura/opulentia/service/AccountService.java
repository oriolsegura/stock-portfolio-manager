package com.oriolsegura.opulentia.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oriolsegura.opulentia.exception.account.AccountNotFoundException;
import com.oriolsegura.opulentia.exception.account.AccountNotOwnedException;
import com.oriolsegura.opulentia.exception.account.InsufficientFundsException;
import com.oriolsegura.opulentia.mapper.CashMovementMapper;
import com.oriolsegura.opulentia.model.CashMovement;
import com.oriolsegura.opulentia.model.OutboxEvent;
import com.oriolsegura.opulentia.repository.CashMovementRepository;
import com.oriolsegura.opulentia.repository.OutboxEventRepository;
import com.oriolsegura.opulentia.request.account.CreateCashMovementRequest;
import com.oriolsegura.opulentia.request.account.CreateAccountRequest;
import com.oriolsegura.opulentia.exception.account.AccountNameAlreadyInUseException;
import com.oriolsegura.opulentia.mapper.AccountMapper;
import com.oriolsegura.opulentia.model.Account;
import com.oriolsegura.opulentia.model.User;
import com.oriolsegura.opulentia.repository.AccountRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

	private final AccountMapper mapper;

	private final AccountRepository repository;

	private final CashMovementMapper cashMovementMapper;

	private final CashMovementRepository cashMovementRepository;

	private final OutboxEventRepository outboxEventRepository;

	public AccountService(
			AccountMapper mapper,
			AccountRepository repository,
			CashMovementMapper cashMovementMapper,
			CashMovementRepository cashMovementRepository,
			OutboxEventRepository outboxEventRepository
	) {
		this.mapper = mapper;
		this.repository = repository;
		this.cashMovementMapper = cashMovementMapper;
		this.cashMovementRepository = cashMovementRepository;
		this.outboxEventRepository = outboxEventRepository;
	}

	public Account createAccount(User user, CreateAccountRequest request) throws AccountNameAlreadyInUseException {
		Account account = mapper.fromCreateRequest(request);
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

	@Transactional
	public Account makeCashMovement(
			User user,
			Long accountId,
			CreateCashMovementRequest request
	) throws AccountNotFoundException, AccountNotOwnedException {
		Account account = repository.findByIdForUpdate(accountId)
				.orElseThrow(() -> new AccountNotFoundException(accountId));

		if (! account.getUserId().equals(user.getId())) {
			throw new AccountNotOwnedException();
		}

		account.applyMovement(request.amount());

		if (account.getBalanceMoney().amount().compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientFundsException();
		}

		repository.save(account);
		repository.flush();

		CashMovement cashMovement = cashMovementMapper.fromCreateRequest(accountId, request);
		cashMovementRepository.save(cashMovement);

		OutboxEvent event = null;

		try {
			event = OutboxEvent.pending(
					"ACCOUNT",
					accountId.toString(),
					account.getVersion(),
					"CASH_MOVEMENT_CREATED",
					cashMovementMapper.toEventJson(cashMovement)
			);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		outboxEventRepository.save(event);

		return account;
	}

}
