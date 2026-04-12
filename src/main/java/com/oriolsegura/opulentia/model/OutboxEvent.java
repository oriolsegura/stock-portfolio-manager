package com.oriolsegura.opulentia.model;

import com.oriolsegura.opulentia.enumeration.EventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "uuid")
	private UUID id;

	@NotNull
	@Column(name = "aggregate_type", length = 100, nullable = false)
	private String aggregateType;

	@NotNull
	@Column(name = "aggregate_id", length = 50, nullable = false)
	private String aggregateId;

	@NotNull
	@Column(name = "aggregate_version", nullable = false)
	private Long aggregateVersion;

	@NotNull
	@Column(name = "event_type", length = 100, nullable = false)
	private String eventType;

	@NotNull
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "JSONB", nullable = false)
	private String payload;

	@NotNull
	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Column(nullable = false)
	private EventStatus status;

	@NotNull
	@Column(name = "retry_count", nullable = false)
	private int retryCount;

	@Column(name = "last_error")
	private String lastError;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "published_at")
	private LocalDateTime publishedAt;

	@Column(name = "failed_at")
	private LocalDateTime failedAt;

	public static OutboxEvent pending(
			String aggregateType,
			String aggregateId,
			Long aggregateVersion,
			String eventType,
			String payload
	) {
		OutboxEvent event = new OutboxEvent();
		event.aggregateType = aggregateType;
		event.aggregateId = aggregateId;
		event.aggregateVersion = aggregateVersion;
		event.eventType = eventType;
		event.payload = payload;
		event.status = EventStatus.PENDING;
		event.retryCount = 0;

		return event;
	}

	public String getEventType() {
		return eventType;
	}

	public String getPayload() {
		return payload;
	}

	public void markAsPublished() {
		status = EventStatus.PUBLISHED;
		publishedAt = LocalDateTime.now();
	}

	public void markAsFailed(Throwable th) {
		status = EventStatus.FAILED;
		failedAt = LocalDateTime.now();
		lastError = th.getMessage();
		retryCount += 1;
	}

}
