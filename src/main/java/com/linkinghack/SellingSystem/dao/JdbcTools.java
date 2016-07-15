package com.linkinghack.SellingSystem.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTools {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public boolean hasUser(String userName,String password){
		boolean hasUser = false;
		try{
			String sql = "SELECT password FROM person WHERE userName=?";
			String pass = this.jdbcTemplate.queryForObject(sql, String.class, userName);
			if(pass.equals(password))
				hasUser = true;
		}catch(Exception e){
			return false;
		}
		return hasUser;
	}

	public int getUserId(String userName){
		int userId = -1;
		try {
			String sql = "SELECT id FROM person WHERE userName=?";
			userId = this.jdbcTemplate.queryForObject(sql, Integer.class, userName);
		}catch (Exception e){
			userId = -1;
			e.printStackTrace();
		}
		return userId;
	}

	public int getUserType(String userName){
		int userType = -1;
		try {
			String sql = "SELECT userType FROM person WHERE userName=?";
			userType = this.jdbcTemplate.queryForObject(sql, Integer.class, userName);
		}catch (Exception e){
			userType = -1; //to show that there is an error;
		}
		return userType;
	}
}
