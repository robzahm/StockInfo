package com.stockinfo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
	private String symbol;
	
	private String name;
	
	public Company(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}
	
	@JsonProperty
	public String getId() {
		return symbol;
	}
	
	@JsonProperty
	public String getSymbol() {
		return symbol;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
}
