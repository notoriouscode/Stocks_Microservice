package com.stock.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stock.entity.Stocks;
import com.stock.repo.StockRepo;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepo stockRepo;
	@Autowired
	StockSectorDeligate sectorDeligate;
	@Autowired
	LoginDelegate loginDelegate;
	
	@Override
	public List<Stocks> getAllStocks() {
		List<Stocks> stockList = stockRepo.findAll();
		//for intercommunication microservice
		sectorDeligate.getAllSectors();
		return stockList;
	}

	@Override
	public Stocks getStockById(int sectorId) {
		Optional<Stocks> stock = stockRepo.findById(sectorId);
		if(stock.isPresent()) {
			return stock.get();
		}
 		return null;
	}

	@Override
	public Stocks createNewStock(Stocks stocks,String authToken) {
		if(loginDelegate.isTokenValid(authToken)) {
			Stocks stock = stockRepo.save(stocks);
			return stock;
		}
		else {
			System.out.println("StockServiceImpl.createNewStock()");
			return null;
		}
	}

	@Override
	public boolean deleteStockById(int stockId) {
		if(stockRepo.existsById(stockId)) {
			stockRepo.deleteById(stockId);
			return true;
		}
		return false;
	}

	@Override
	public Stocks updateStockById(int stockId, Stocks stocks) {
		Optional<Stocks> opSectorEntity = stockRepo.findById(stockId);
		if(opSectorEntity.isPresent()) {
			Stocks mStocks = opSectorEntity.get();
			mStocks.setStock_price(stocks.getStock_price());
			stockRepo.save(mStocks);
			return mStocks;
		}
		return null;
	}
}

//
//http://localhost:4000/mymarketplace/sector
//{"sector_name":"NIFITY_50","sector_price":"5000","opening_price":"6000",
//"closing_price":"7000","sector_current_volumn":"154000000"} 