package com.stockinfo.app;

import com.stockinfo.health.CompanyHealthCheck;
import com.stockinfo.resources.StockInfoResource;

import io.dropwizard.Application;
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
    public void run(StockInfoConfiguration configuration, Environment environment) {
        final StockInfoResource resource = new StockInfoResource();
        final CompanyHealthCheck healthCheck = new CompanyHealthCheck();
        
        environment.healthChecks().register("company", healthCheck);
        environment.jersey().register(resource);
    }
}
