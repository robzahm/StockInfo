package com.stockinfo.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.stockinfo.dao.CompanyDAO;
import com.stockinfo.model.Company;
import com.stockinfo.model.CompanyWrapper;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {
	
	private CompanyDAO companyDao;
	
	public CompanyResource(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	/**
	 * Return a list of companies that are prefixed with the name parameter
	 * @param name
	 * @return
	 */
	@GET
	@Timed
	public CompanyWrapper findCompaniesByName(@QueryParam("name") String name) {
		// TODO: Require a minimum number of characters
		// TODO: Respond with graceful error messages
		// Execute the query and return the list wrapper
		List<Company> companies = companyDao.findByName(name);
		CompanyWrapper response = new CompanyWrapper(companies);
		return response;
    }
}
