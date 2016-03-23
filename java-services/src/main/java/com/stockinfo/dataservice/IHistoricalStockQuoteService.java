package com.stockinfo.dataservice;

import java.util.Date;

import com.stockinfo.model.StockQuoteWrapper;

/**
 * Service to provide historical quotes from a data source
 * @author zahm
 *
 */
public interface IHistoricalStockQuoteService {
	/**
	 * Returns the stock price history for a given stock, between two dates
	 * @param symbol
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public StockQuoteWrapper getStockHistory(String symbol, Date start, Date end) throws Exception;
}
