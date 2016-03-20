package com.stockinfo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class StockQuote {
	private String symbol;
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	
	public StockQuote(String symbol, String date, double open, double high, double low, double close) {
		super();
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}

	@JsonProperty
	public String getSymbol() {
		return symbol;
	}

	@JsonProperty
	public String getDate() {
		return date;
	}

	@JsonProperty
	public double getOpen() {
		return open;
	}

	@JsonProperty
	public double getHigh() {
		return high;
	}

	@JsonProperty
	public double getLow() {
		return low;
	}

	@JsonProperty
	public double getClose() {
		return close;
	}
	
	@JsonProperty
	public double getAveragePrice() {
		return (open + high + low + close) / 4;
	}
}
