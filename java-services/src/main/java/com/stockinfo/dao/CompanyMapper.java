package com.stockinfo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.stockinfo.model.Company;

/**
 * Provides the JDBI class mapping
 * @author zahm
 *
 */
public class CompanyMapper implements ResultSetMapper<Company>
{
  public Company map(int index, ResultSet r, StatementContext ctx) throws SQLException
  {
    return new Company(r.getString("symbol"), r.getString("name"));
  }
}