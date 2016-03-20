package com.stockinfo.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyWrapper {
	private final List<Company> companies;
	
	public CompanyWrapper(List<Company> companies) {
		this.companies = companies;
	}
	
	@JsonProperty
	public List<Company> getCompanies(){
		return companies;
	}
}
