package com.stock.service;


public interface LoginDelegate {
	public boolean isTokenValid(String authToken);
}
