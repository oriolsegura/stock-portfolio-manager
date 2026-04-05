package com.oriolsegura.opulentia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_id_seq")
	@SequenceGenerator(name = "stocks_id_seq", sequenceName = "stocks_id_seq")
	private Long id;

	@NotNull
	@Column(name = "company_name", length = 255, nullable = false, unique = true)
	private String companyName;

	@NotNull
	@Column(name = "ticker_symbol", length = 7, nullable = false, unique = true)
	private String ticker;

	@NotNull
	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "currency_code", length = 3, nullable = false)
	private String currencyCode;

	@Column(name = "price", precision = 19, scale = 4)
	private BigDecimal price;

	@Column(name = "price_updated_at")
	private LocalDateTime priceUpdatedAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getTicker() {
		return ticker;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public void setLastPrice(@NotNull BigDecimal price, @NotNull LocalDateTime priceUpdatedAt) {
		if (price.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The price must be positive");
		}

		if (this.priceUpdatedAt != null && ! priceUpdatedAt.isAfter(this.priceUpdatedAt)) {
			throw new IllegalArgumentException("The current price value is already later than the new price");
		}

		if (priceUpdatedAt.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("The price update time cannot be in the future");
		}

		this.price = price;
		this.priceUpdatedAt = priceUpdatedAt;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
