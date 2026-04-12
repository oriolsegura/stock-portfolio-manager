package com.oriolsegura.opulentia.service;

import com.oriolsegura.opulentia.message.CashMovementMessage;
import com.oriolsegura.opulentia.model.OutboxEvent;
import com.oriolsegura.opulentia.producer.CashMovementMessageProducer;
import com.oriolsegura.opulentia.repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class OutboxPublisher {

	private final OutboxEventRepository repository;

	private final CashMovementMessageProducer cashMovementProducer;

	private final ObjectMapper objectMapper;

	public OutboxPublisher(
			OutboxEventRepository repository,
			CashMovementMessageProducer cashMovementProducer,
			ObjectMapper objectMapper
	) {
		this.repository = repository;
		this.cashMovementProducer = cashMovementProducer;
		this.objectMapper = objectMapper;
	}

	@Transactional
	public void publishBatch(int limit) {
		if (limit <= 0) {
			throw new IllegalArgumentException("The limit must be greater than 0");
		}

		List<OutboxEvent> events = repository.fetchPendingForProcessing(limit);

		for (OutboxEvent event : events) {
			try {
				publish(event);
				event.markAsPublished();
			} catch (Throwable th) {
				event.markAsFailed(th);
			}
		}
	}

	private void publish(OutboxEvent event) {
		if (event.getEventType().equals("CASH_MOVEMENT_CREATED")) {
			CashMovementMessage message = deserialize(event.getPayload());
			cashMovementProducer.sendMessage(message);
		}
	}

	private CashMovementMessage deserialize(String json) {
		try {
			return objectMapper.readValue(json, CashMovementMessage.class);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

}
