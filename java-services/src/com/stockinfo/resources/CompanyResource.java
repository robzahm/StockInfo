package com.stockinfo.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.stockinfo.api.Company;
import com.stockinfo.api.CompanyWrapper;
import com.stockinfo.dao.CompanyDAO;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {
	
	private CompanyDAO companyDao;
	
	public CompanyResource(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	@GET
	@Timed
	public CompanyWrapper findCompaniesByName(@QueryParam("name") String name) {
		// TODO: Require a minimum number of characters
		// Execute the query and return the list wrapper
		List<Company> companies = companyDao.findByName(name);
		CompanyWrapper response = new CompanyWrapper(companies);
		return response;
    }
}
