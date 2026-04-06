package com.oriolsegura.opulentia.mapper;

import com.oriolsegura.opulentia.dto.account.AccountDto;
import com.oriolsegura.opulentia.dto.account.CreateAccountDto;
import com.oriolsegura.opulentia.model.Account;
import com.oriolsegura.opulentia.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

	public AccountDto toDto(Account account) {
		Money money = account.getBalanceMoney();

		return new AccountDto(account.getId(), account.getName(), money.amount(), money.currency().getCurrencyCode());
	}

	public Account fromCreateRequest(CreateAccountDto data) {
		Account account = new Account();
		account.setName(data.name());
		account.initBalance();
		account.setCurrencyCode(data.currencyCode().toUpperCase());

		return account;
	}

}
