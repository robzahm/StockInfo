package com.stockinfo.app;

import org.skife.jdbi.v2.DBI;

import com.stockinfo.dao.CompanyDAO;
import com.stockinfo.health.CompanyHealthCheck;
import com.stockinfo.resources.StockInfoResource;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    public void run(StockInfoConfiguration configuration, Environment environment) throws ClassNotFoundException {
    	// Initialize the database layer
    	final DBIFactory factory = new DBIFactory();
	    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
	    
	    // Create and register the DAO
	    final CompanyDAO dao = jdbi.onDemand(CompanyDAO.class);
	    environment.jersey().register(new StockInfoResource(dao));
    	
	    // Create an register the resource endpoints
        final StockInfoResource resource = new StockInfoResource(dao);
        environment.jersey().register(resource);
        
        // Create and register the health checks
        final CompanyHealthCheck healthCheck = new CompanyHealthCheck();
        environment.healthChecks().register("company", healthCheck);
    }
}
