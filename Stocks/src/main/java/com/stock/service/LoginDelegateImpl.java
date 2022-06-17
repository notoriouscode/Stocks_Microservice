package com.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDelegate {

	@Autowired
	@Lazy
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "AUTH-TOKEN-VALIDATION", fallbackMethod = "fallbackIsTokenValid")
	public boolean isTokenValid(String authToken) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		try {
			ResponseEntity<Boolean> response = this.restTemplate
					.exchange("http://API-GATEWAY/mymarketplace/login/token/validate", HttpMethod.GET, entity, Boolean.class);
			if (response.getStatusCode() == HttpStatus.OK)
				return response.getBody();
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean fallbackIsTokenValid(String authToken, Exception ex) {
		System.out.println("LoginDelegateImpl.fallbackIsTokenValid()");
		return false;
	}
}
