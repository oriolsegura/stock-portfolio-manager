package com.oriolsegura.opulentia.schedule;

import com.oriolsegura.opulentia.service.OutboxPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxScheduler {

	private final OutboxPublisher publisher;

	public OutboxScheduler(OutboxPublisher publisher) {
		this.publisher = publisher;
	}

	@Scheduled(fixedDelay = 2000)
	public void run() {
		publisher.publishBatch(50);
	}

}
