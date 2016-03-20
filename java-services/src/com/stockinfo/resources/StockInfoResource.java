package com.stockinfo.resources;

import com.stockinfo.api.Company;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class StockInfoResource {
	
	public StockInfoResource() {
	}
	
	@GET
	@Timed
	public Company findCompanies(@QueryParam("name") Optional<String> name) {
		String theName = null;
		if (name.isPresent()) {
			theName = name.get();
		}
		return new Company(1, "AAA", theName);
    }
}
