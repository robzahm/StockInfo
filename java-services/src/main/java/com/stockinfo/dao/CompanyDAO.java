package com.stockinfo.dao;


import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.stockinfo.model.Company;

/**
 * Database access interface, JDBI implementation provided automatically based on the signature
 * @author zahm
 *
 */
@RegisterMapper(CompanyMapper.class)
public interface CompanyDAO {
	// Filter out symbols with carats - this looks like bad data in the CSVs, and we can't get quotes for it
	@SqlQuery("select symbol, name from company where name like concat(:name, '%') and symbol not like '%^%' order by name asc limit 50")
	List<Company> findByName(@Bind("name") String name);
}

