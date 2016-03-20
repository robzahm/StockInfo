package com.stockinfo.dao;


import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.stockinfo.api.Company;

@RegisterMapper(CompanyMapper.class)
public interface CompanyDAO {

	  @SqlQuery("select id, symbol, name from companies where name like :name")
	  List<Company> findByName(@Bind("name") String name);
	  
	  @SqlQuery("select name from companies where id = :id")
	  String findNameById(@Bind("id") int id);
	  
	  @SqlQuery("select id, symbol, name from companies where id = :id")
	  Company findById(@Bind("id") int id);
}

