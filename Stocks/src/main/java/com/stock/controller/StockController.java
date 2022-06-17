package com.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stock.entity.Stocks;
import com.stock.service.StockService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mymarketplace") 
public class StockController {

	@Autowired
	StockService stockService;

		@PostMapping(value = "/stock", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
		public ResponseEntity<Stocks> createNewStock(@RequestBody Stocks stocks, @RequestHeader("auth-token") String authToken) { 
			Stocks newStock = stockService.createNewStock(stocks,authToken);
			return new ResponseEntity<Stocks>(newStock, HttpStatus.OK);
		}
		
		@GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Stocks> getAllStocks() {
			return stockService.getAllStocks();
		}
		
		//http://localhost:5000/mymarketplace/stock/1
		@GetMapping(value = "/stock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Stocks getStockById(@PathVariable("id") int stockId) {
			return stockService.getStockById(stockId);
		}
		
		@DeleteMapping(value="/stock/{id}")
		public ResponseEntity<Boolean> deleteStockById(@PathVariable("id") int stockId) {
			return new ResponseEntity<Boolean>(stockService.deleteStockById(stockId), HttpStatus.OK);
		}

		@PutMapping(value="/stock/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, 
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Stocks> updateStockById(@PathVariable("id") int stockId, @RequestBody Stocks stocks) {
			return new ResponseEntity<Stocks>(stockService.updateStockById(stockId, stocks), HttpStatus.OK);
		}
}
