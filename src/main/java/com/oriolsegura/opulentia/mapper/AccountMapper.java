package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.dto.account.AccountDto;
import com.oriolsegura.opulentia.request.account.CreateAccountRequest;
import com.oriolsegura.opulentia.model.Account;
import com.oriolsegura.opulentia.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

	public AccountDto toDto(Account account) {
		Money money = account.getBalanceMoney();

		return new AccountDto(account.getId(), account.getName(), money.amount(), money.currency().getCurrencyCode());
	}

	public Account fromCreateRequest(CreateAccountRequest request) {
		Account account = new Account();
		account.setName(request.name());
		account.initBalance();
		account.setCurrencyCode(request.currencyCode().toUpperCase());

		return account;
	}

}
