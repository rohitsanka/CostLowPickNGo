package com.java.dao;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.bean.Person;

@Repository

public class AdminDAO {
	JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addCustomer(Person person) {
		// TODO Auto-generated method stub
		 String sql1= "INSERT INTO CUSTOMER_TABLE (" +"USERNAME,"+"PASSWORD,"+"FIRST_NAME,"+"LAST_NAME,"+"EMAIL)"+"VALUES(?,?,?,?,?)";
		 Object[] params = new Object[] { person.getUserName(), person.getPassword(), person.getFirstName(),person.getLastName(),person.getEmail()};
          int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
          int row = jdbcTemplate.update(sql1, params, types);

		
	}

	

	public void addEmployee(Person person) {
		// TODO Auto-generated method stub
		String sql1= "INSERT INTO EMPLOYEE_TABLE (" +"USERNAME,"+"PASSWORD,"+"ROLE,"+"FIRST_NAME,"+"LAST_NAME,"+"EMAIL)"+"VALUES(?,?,?,?,?,?)";
		 Object[] params = new Object[] { person.getUserName(), person.getPassword(),person.getRole(), person.getFirstName(),person.getLastName(),person.getEmail()};
         int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
         int row = jdbcTemplate.update(sql1, params, types);
		
	}

	

}
