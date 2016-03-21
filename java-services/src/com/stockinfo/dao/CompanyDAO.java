package com.stockinfo.dao;


import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.stockinfo.api.Company;

@RegisterMapper(CompanyMapper.class)
public interface CompanyDAO {
	// TODO: Add exchange
	@SqlQuery("select symbol, name from company where name like concat(:name, '%') order by name asc limit 50")
	List<Company> findByName(@Bind("name") String name);
}

