package com.stockinfo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockQuoteWrapper {
	private final List<StockQuote> stockQuotes;
	
	public StockQuoteWrapper(List<StockQuote> stockQuotes) {
		this.stockQuotes = stockQuotes;
	}
	
	@JsonProperty("quotes")
	public List<StockQuote> getStockQuotes(){
		return stockQuotes;
	}
}
