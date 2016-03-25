package com.stockinfo.dataservice.yahoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockinfo.dataservice.IHistoricalStockQuoteService;
import com.stockinfo.dataservice.yahoo.model.Quote;
import com.stockinfo.dataservice.yahoo.model.Wrapper;
import com.stockinfo.model.StockQuote;
import com.stockinfo.model.StockQuoteWrapper;

/**
 * This class provides an implementation that provides historical stock
 * quotes from Yahoo
 * @author zahm
 *
 */
public class YahooStockService implements IHistoricalStockQuoteService {
	
	// Date format used by the Yahoo service
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	private HttpClient httpClient;
	
	public YahooStockService(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	/**
	 * Implementation that get the stock price, and returns it in the application's model classes
	 */
	public StockQuoteWrapper getStockHistory(String symbol, Date start, Date end) throws Exception {
		// Build the Yahoo query with the correct inputs
		String query = String.format(
			"select * from yahoo.finance.historicaldata where symbol = \"%s\" and startDate = \"%s\" and endDate = \"%s\"",
			symbol, dateFormatter.format(start), dateFormatter.format(end));
		
		// Encode the URL, including replacing the + with %20 as an extra step
		String encodedQuery = URLEncoder.encode(query, "UTF-8");
		encodedQuery = encodedQuery.replace("+", "%20");
		
		// Build the URL to the Yahoo data source
		StringBuilder theUrl = new StringBuilder();
		theUrl.append("https://query.yahooapis.com/v1/public/yql?q=");
		theUrl.append(encodedQuery);
		theUrl.append("&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
		
		// Submit the request to Yahoo
		HttpGet request = new HttpGet(theUrl.toString());
		HttpResponse response = httpClient.execute(request);
		
		// Read the response into a string
		// TODO: Stream direct to JSON/Object?
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder strResponse = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			strResponse.append(line);
		}
		
		// Convert the string response to Yahoo model objects
		ObjectMapper mapper = new ObjectMapper();
		Wrapper yahooWrapper = mapper.readValue(strResponse.toString(), Wrapper.class);
		
		// Begin building the StockInfo model objects to return, using the Yahoo model as the source
		ArrayList<StockQuote> respList = new ArrayList<StockQuote>(yahooWrapper.query.results.quote.length);
		
		for (int i = 0; i < yahooWrapper.query.results.quote.length; i++) {
			Quote yahooQuote =  yahooWrapper.query.results.quote[i];
			StockQuote quote = new StockQuote(yahooQuote.symbol, yahooQuote.date, yahooQuote.open,
					yahooQuote.high, yahooQuote.low, yahooQuote.close);
			
			// Add to the beginning so that the oldest data is first
			// TODO: Sort by date instead for a more robust solution
			respList.add(0, quote);
		}
		
		return new StockQuoteWrapper(respList);
	}
}
