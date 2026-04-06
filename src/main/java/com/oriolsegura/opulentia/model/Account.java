package com.oriolsegura.opulentia.model;

import com.oriolsegura.opulentia.valueobject.Money;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accounts_id_seq")
	@SequenceGenerator(name = "accounts_id_seq", sequenceName = "accounts_id_seq", allocationSize = 50)
	private Long id;

	@NotNull
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@NotNull
	@Column(length = 63, nullable = false)
	private String name;

	@NotNull
	@Column(precision = 19, scale = 4, nullable = false)
	private BigDecimal balance;

	@NotNull
	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "currency_code", length = 3, nullable = false)
	private String currencyCode;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Money getBalanceMoney() {
		return Money.of(balance, currencyCode);
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void initBalance() {
		if (this.balance != null) {
			throw new IllegalStateException("Balance is already initialized");
		}

		this.balance = BigDecimal.ZERO;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
