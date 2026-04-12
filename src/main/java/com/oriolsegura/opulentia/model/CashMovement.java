package com.oriolsegura.opulentia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_movements")
public class CashMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cash_movements_id_seq")
	@SequenceGenerator(name = "cash_movements_id_seq", sequenceName = "cash_movements_id_seq")
	private Long id;

	@NotNull
	@Column(name = "account_id", nullable = false, updatable = false)
	private Long accountId;

	@NotNull
	@Column(precision = 19, scale = 4, nullable = false, updatable = false)
	private BigDecimal amount;

	@Column(length = 127)
	private String description;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Long getAccountId() {
		return accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
