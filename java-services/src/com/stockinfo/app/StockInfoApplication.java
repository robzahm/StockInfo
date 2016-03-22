package com.stockinfo.app;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.apache.http.client.HttpClient;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import com.stockinfo.dao.CompanyDAO;
import com.stockinfo.dataservice.IHistoricalStockQuoteService;
import com.stockinfo.dataservice.yahoo.YahooStockService;
import com.stockinfo.health.CompanyHealthCheck;
import com.stockinfo.resources.CompanyResource;
import com.stockinfo.resources.StockPriceResource;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Application class responsible for initialization & registration
 * @author zahm
 *
 */
public class StockInfoApplication extends Application<StockInfoConfiguration> {
	public static void main(String[] args) throws Exception {
		new StockInfoApplication().run(args);
	}
	
    @Override
    public String getName() {
        return "stock-info";
    }

    @Override
    public void initialize(Bootstrap<StockInfoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(StockInfoConfiguration configuration, Environment environment) {
    	
    	// Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	
    	// Initialize the database layer
    	final DBIFactory factory = new DBIFactory();
	    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
	    
	    // Create and register the Company DAO
	    final CompanyDAO dao = jdbi.onDemand(CompanyDAO.class);
    	
	    // Create an register the Company resource endpoint
        final CompanyResource companyResource = new CompanyResource(dao);
        environment.jersey().register(companyResource);
        
        // Create and register the health checks
        // TODO: Remove or fully implement
        final CompanyHealthCheck healthCheck = new CompanyHealthCheck();
        environment.healthChecks().register("company", healthCheck);
        
        // HTTP Client Configuration
        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration()).build("example-http-client");
        
        // Yahoo Service Registration
        IHistoricalStockQuoteService yahooService = new YahooStockService(httpClient);
        environment.jersey().register(yahooService);
        
        // Stock Price Resource registration
        environment.jersey().register(new StockPriceResource(yahooService));
    }
}
