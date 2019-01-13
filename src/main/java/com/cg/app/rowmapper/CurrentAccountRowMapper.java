package com.cg.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.pojo.account.CurrentAccount;


public class CurrentAccountRowMapper implements RowMapper<CurrentAccount>{
	
	 public CurrentAccount mapRow(ResultSet rs, int rownumber) throws SQLException {  
		 CurrentAccount currentAccount=new CurrentAccount(rs.getInt("account_id"),rs.getString("account_hn"),rs.getDouble(3),rs.getDouble(5)); 
		 return currentAccount;  
	  }
}
