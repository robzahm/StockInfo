package com.stockinfo.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
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
	public CompanyWrapper findCompanies(@QueryParam("name") Optional<String> name) {
		// Ensure that we have a name passed in
		String theName = null;
		if (name.isPresent()) {
			theName = name.get();
		}
		
		// Execute the query and return
		// TODO: Move the like param into the DAO
		//return companyDao.findByName(theName + "%");
		List<Company> companies = companyDao.findByName("%");
		CompanyWrapper response = new CompanyWrapper(companies);
		return response;
    }
}
