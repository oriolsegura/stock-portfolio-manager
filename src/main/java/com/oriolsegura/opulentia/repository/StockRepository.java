package com.oriolsegura.opulentia.repository;

import com.oriolsegura.opulentia.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

	//

}
