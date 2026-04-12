package com.oriolsegura.opulentia.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oriolsegura.opulentia.event.CashMovementEvent;
import com.oriolsegura.opulentia.model.CashMovement;
import com.oriolsegura.opulentia.request.account.CreateCashMovementRequest;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class CashMovementMapper {

	private final ObjectMapper objectMapper;

	public CashMovementMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String toEventJson(CashMovement cashMovement) throws JsonProcessingException {
		return objectMapper.writeValueAsString(toEvent(cashMovement));
	}

	public CashMovementEvent toEvent(CashMovement cashMovement) {
		return new CashMovementEvent(
				cashMovement.getAccountId(),
				cashMovement.getAmount(),
				cashMovement.getDescription()
		);
	}

	public CashMovement fromCreateRequest(Long accountId, CreateCashMovementRequest request) {
		CashMovement cashMovement = new CashMovement();
		cashMovement.setAccountId(accountId);
		cashMovement.setAmount(request.amount());
		cashMovement.setDescription(request.description());

		return cashMovement;
	}

}
