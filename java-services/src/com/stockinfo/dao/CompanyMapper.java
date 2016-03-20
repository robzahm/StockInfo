package com.stockinfo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.stockinfo.api.Company;

// TODO: Place in a separate package
public class CompanyMapper implements ResultSetMapper<Company>
{
  public Company map(int index, ResultSet r, StatementContext ctx) throws SQLException
  {
    return new Company(r.getInt("id"), r.getString("symbol"), r.getString("name"));
  }
}