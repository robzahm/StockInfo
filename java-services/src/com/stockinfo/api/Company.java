package com.stockinfo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
	private int id;
	
	private String symbol;
	
	private String name;
	
	public Company(int id, String symbol, String name) {
		this.id = id;
		this.symbol = symbol;
		this.name = name;
	}
	
	@JsonProperty
	public int getId() {
		return id;
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
