package com.oriolsegura.opulentia.producer;

import com.oriolsegura.opulentia.message.CashMovementMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CashMovementMessageProducer {

	@Value("${spring.kafka.topics.cash-movements}")
	private String topic;

	private final KafkaTemplate<String, CashMovementMessage> kafkaTemplate;

	public CashMovementMessageProducer(KafkaTemplate<String, CashMovementMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(CashMovementMessage message) {
		kafkaTemplate.send(topic, message.accountId().toString(), message);
	}

}
