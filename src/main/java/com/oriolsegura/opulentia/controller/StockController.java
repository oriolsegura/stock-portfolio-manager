package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.dto.stock.CreateStockDto;
import com.oriolsegura.opulentia.dto.stock.StockDto;
import com.oriolsegura.opulentia.mapper.StockMapper;
import com.oriolsegura.opulentia.model.Stock;
import com.oriolsegura.opulentia.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	private final StockService stockService;

	private final StockMapper stockMapper;

	public StockController(StockService stockService, StockMapper stockMapper) {
		this.stockService = stockService;
		this.stockMapper = stockMapper;
	}

	@PostMapping
	public ResponseEntity<StockDto> create(@RequestBody @Valid CreateStockDto data) {
		Stock stock = stockService.createStock(data);
		StockDto stockDto = stockMapper.toDto(stock);

		return ResponseEntity.status(HttpStatus.CREATED).body(stockDto);
	}

	@GetMapping
	public ResponseEntity<List<StockDto>> catalog() {
		List<StockDto> stocks = stockService.getAllStocks()
				.stream()
				.map(stockMapper::toDto)
				.toList();

		return ResponseEntity.ok(stocks);
	}

}
