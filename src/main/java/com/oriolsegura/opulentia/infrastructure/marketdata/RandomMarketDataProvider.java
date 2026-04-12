package com.oriolsegura.opulentia.infrastructure.marketdata;

import com.oriolsegura.opulentia.model.Stock;
import com.oriolsegura.opulentia.provider.MarketDataProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component("RandomProvider")
public class RandomMarketDataProvider implements MarketDataProvider {

	@Override
	public BigDecimal getCurrentPrice(Stock stock) {
		BigDecimal factor = BigDecimal
				.valueOf(ThreadLocalRandom.current().nextInt(-10, 12))
				.divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);

		return stock.getPrice().multiply(BigDecimal.ONE.add(factor));
	}

	@Override
	public HashMap<Long, BigDecimal> getCurrentPrice(List<Stock> stocks) {
		return stocks.stream().collect(
				HashMap::new,
				(map, stock) -> map.put(stock.getId(), getCurrentPrice(stock)),
				HashMap::putAll
		);
	}

}
