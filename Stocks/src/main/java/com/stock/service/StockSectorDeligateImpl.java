package com.stock.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class StockSectorDeligateImpl implements StockSectorDeligate {

	@Autowired
	@Lazy
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name="SECTOR-CIRCUIT-BREAKER",fallbackMethod = "fallbackGetAllSectors")
	public List<Map> getAllSectors() {
		List sectors=this.restTemplate.getForObject("http://API-GATEWAY/mymarketplace/sector", List.class);
		return sectors;
	}
	
	public List<Map> fallbackGetAllSectors(Exception ex) {
		System.out.println("StockSectorDeligateImpl.fallbackGetAllSectors()");
		return null;
	}
}
