package com.stockinfo.dataservice;

import java.util.Date;

import com.stockinfo.model.StockQuoteWrapper;

public interface IHistoricalStockQuoteService {
	
	public StockQuoteWrapper getStockHistory(String symbol, Date start, Date end) throws Exception;
}
