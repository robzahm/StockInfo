package com.stockinfo.resources;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.stockinfo.dataservice.IHistoricalStockQuoteService;
import com.stockinfo.model.StockQuoteWrapper;

@Path("/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class StockPriceResource {
	
	private IHistoricalStockQuoteService historicalQuoteService;
	
	public StockPriceResource(IHistoricalStockQuoteService historicalQuoteService) {
		this.historicalQuoteService = historicalQuoteService;
	}
	
	/**
	 * Return a list of historical quotes for a given stock symbol
	 * If the symbol is not found, an Exception will be thrown up from this layer,
	 * this could be improved to return JSON-formatted error messages
	 * @param symbol
	 * @return
	 */
	@GET
	@Timed
	public StockQuoteWrapper getHistoricalQuotes(@QueryParam("symbol") String symbol) {
		
		StockQuoteWrapper wrapper = null;
		try
		{
			// Get objects for the current date, and the date 30 days ago
			Date currentDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, -30);
			Date thirtyDaysAgo = cal.getTime();
			
			// Call the historical service to get the list of stock prices
			wrapper = historicalQuoteService.getStockHistory(symbol, thirtyDaysAgo, currentDate);
			
		} catch (Exception e) {
			// TODO: Logging
			// TODO: Respond with graceful error messages
			e.printStackTrace();
		}
		
		return wrapper;
    }
}
