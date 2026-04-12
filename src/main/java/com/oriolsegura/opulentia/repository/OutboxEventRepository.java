package com.oriolsegura.opulentia.repository;

import com.oriolsegura.opulentia.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {

	@Query(value = """
		SELECT * FROM outbox_events
		WHERE status = 'PENDING'
		ORDER BY created_at
		FOR UPDATE SKIP LOCKED
		LIMIT :limit
	""", nativeQuery = true)
	List<OutboxEvent> fetchPendingForProcessing(int limit);

}
