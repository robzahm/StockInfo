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
import com.stockinfo.dao.CompanyDAO;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class StockInfoResource {
	private CompanyDAO companyDao;
	
	public StockInfoResource(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	@GET
	@Timed
	public List<Company> findCompanies(@QueryParam("name") Optional<String> name) {
		System.out.println("Find Companies");
		String theName = null;
		if (name.isPresent()) {
			theName = name.get();
		}
		
		
		String fullName = companyDao.findNameById(2);
		//return new Company(1, "AAA", fullName);
		
		Company fullCompany = companyDao.findById(2);
		//return fullCompany;
		
		List<Company> companies = companyDao.findByName(theName + "%");
		return companies;
		
		//Iterator<Company> companies = companyDao.findByName(theName);
		//return companies.next();
		//return new Company(1, "AAA", theName);
    }
}
