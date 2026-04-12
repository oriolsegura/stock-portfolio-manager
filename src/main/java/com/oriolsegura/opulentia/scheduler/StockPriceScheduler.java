package com.oriolsegura.opulentia.scheduler;

import com.oriolsegura.opulentia.service.StockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockPriceScheduler {

	private final StockService stockService;

	public StockPriceScheduler(StockService stockService) {
		this.stockService = stockService;
	}

	@Scheduled(fixedRate = 5000)
	public void updateStockPrices() {
		stockService.updatePrices();
	}

}
