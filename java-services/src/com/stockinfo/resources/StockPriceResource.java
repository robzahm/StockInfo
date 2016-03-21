package com.stockinfo.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockinfo.api.StockQuote;
import com.stockinfo.api.StockQuoteWrapper;
import com.stockinfo.yahoo.model.Quote;
import com.stockinfo.yahoo.model.Wrapper;

@Path("/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class StockPriceResource {
	
	// Could put in an app-wide utils class
	
	
	private HttpClient httpClient;
	
	public StockPriceResource(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	@GET
	@Timed
	public StockQuoteWrapper findCompaniesByName(@QueryParam("symbol") String symbol) {
		
		// TODO: Refactor - pull all Yahoo references into a single location
		// TODO: Build out the dates
		
		StockQuoteWrapper wrapper = null;
		String strYaho = "";
		try
		{
			// Get objects for the current date, and the date 30 days ago
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, -30);
			Date thirtyDaysAgo = cal.getTime();
			
			// Build the Yahoo query with the correct inputs
			String query = String.format(
					"select * from yahoo.finance.historicaldata where symbol = \"%s\" and startDate = \"%s\" and endDate = \"%s\"",
					symbol, dateFormatter.format(thirtyDaysAgo), dateFormatter.format(currentDate));
			
			String encodedQuery = URLEncoder.encode(query, "UTF-8");
			encodedQuery = encodedQuery.replace("+", "%20");
			
			StringBuilder theUrl = new StringBuilder();
			theUrl.append("https://query.yahooapis.com/v1/public/yql?q=");
			theUrl.append(encodedQuery);
			theUrl.append("&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
			
			
			System.out.println(theUrl.toString());
			
			HttpGet request = new HttpGet(theUrl.toString());
			HttpResponse response = httpClient.execute(request);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); 
			String line = "";
			
			while ((line = rd.readLine()) != null) {
				strYaho = strYaho + line;
			} 
			System.out.println(strYaho);
			
			ObjectMapper mapper = new ObjectMapper();
			Wrapper yahooWrapper = mapper.readValue(strYaho, Wrapper.class);
			System.out.println(yahooWrapper);
			
			ArrayList respList = new ArrayList<StockQuote>(yahooWrapper.query.results.quote.length);
			
			// Build server response objects
			for (int i = 0; i < yahooWrapper.query.results.quote.length; i++) {
				Quote yahooQuote =  yahooWrapper.query.results.quote[i];
				StockQuote quote = new StockQuote(yahooQuote.symbol, yahooQuote.date, yahooQuote.open,
						yahooQuote.high, yahooQuote.low, yahooQuote.close);
				
				// Add to the beginning so that the oldest data is first
				// TODO: Sort by date instead for a more robust solution
				respList.add(0, quote);
			}
			
			wrapper = new StockQuoteWrapper(respList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wrapper;
    }
}
