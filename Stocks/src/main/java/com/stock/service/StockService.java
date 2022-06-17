package com.stock.service;

import java.util.List;
import com.stock.entity.Stocks;

public interface StockService {
	List<Stocks> getAllStocks();
	Stocks getStockById(int stockId);
	Stocks createNewStock(Stocks stocks, String authToken);
	boolean deleteStockById(int stockId);
	Stocks updateStockById(int stockId, Stocks stocks);
}
