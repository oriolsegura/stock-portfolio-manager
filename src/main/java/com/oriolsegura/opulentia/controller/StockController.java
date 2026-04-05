package com.oriolsegura.opulentia.controller;

import com.oriolsegura.opulentia.dto.stock.CreateStockDto;
import com.oriolsegura.opulentia.dto.stock.StockDto;
import com.oriolsegura.opulentia.model.Stock;
import com.oriolsegura.opulentia.repository.StockRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	private final StockRepository repository;

	public StockController(StockRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<StockDto> create(@RequestBody @Valid CreateStockDto data) {
		Stock stock = repository.save(data.toEntity());

		return new ResponseEntity<>(StockDto.fromEntity(stock), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<StockDto>> catalog() {
		List<StockDto> stocks = repository.findAll()
				.stream()
				.map(StockDto::fromEntity)
				.toList();

		return ResponseEntity.ok(stocks);
	}

}
