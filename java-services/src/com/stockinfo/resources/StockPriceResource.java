package com.stockinfo.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.codahale.metrics.annotation.Timed;

@Path("/quotes")
@Produces(MediaType.APPLICATION_JSON)
public class StockPriceResource {
	
	private HttpClient httpClient;
	
	public StockPriceResource(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	@GET
	@Timed
	public String findCompaniesByName(@QueryParam("name") String name) {
		try
		{
			String query = "select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"2016-03-01\" and endDate = \"2016-03-10\"";
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
			String strYaho = "";
			while ((line = rd.readLine()) != null) {
				strYaho = strYaho + line;
			} 
			System.out.println(strYaho);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Quotes";
    }
}
