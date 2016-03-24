package com.stockinfo.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockQuote {
	
	private String symbol;
	private String date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	
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
	public String getId() {
		return symbol + "_" + date;
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
		return new BigDecimal(open).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	@JsonProperty
	public double getHigh() {
		return new BigDecimal(high).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@JsonProperty
	public double getLow() {
		return new BigDecimal(low).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@JsonProperty
	public double getClose() {
		return new BigDecimal(close).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	@JsonProperty
	public double getAveragePrice() {
		double average = (open + high + low + close) / 4;
		return new BigDecimal(average).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
