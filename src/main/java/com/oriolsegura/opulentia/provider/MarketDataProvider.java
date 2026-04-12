package com.oriolsegura.opulentia.provider;

import com.oriolsegura.opulentia.model.Stock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface MarketDataProvider {

	BigDecimal getCurrentPrice(Stock stock);

	HashMap<Long, BigDecimal> getCurrentPrice(List<Stock> stocks);

}
