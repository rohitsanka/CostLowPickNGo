package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.bean.Login;
import com.java.bean.Product;

@Repository
public class LoginDAO {
	JdbcTemplate jdbcTemplate;
	@Autowired

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public Login userLogin(Login login) {
		Login l=new Login();
		try{
			
			
			
			String sql1="select * from EMPLOYEE_TABLE where USERNAME=? and PASSWORD=?"; 
			System.out.println("start");
		    l= jdbcTemplate.queryForObject(sql1, new Object[]{login.getUserName(),login.getPassword()},new BeanPropertyRowMapper<Login>(Login.class));  
			
		    System.out.println("admin");

		}
			catch (EmptyResultDataAccessException e) {

		    	System.out.println("customer");
			String sql="select * from CUSTOMER_TABLE where USERNAME=? and PASSWORD=?"; 
			try{
				 l= jdbcTemplate.queryForObject(sql, new Object[]{login.getUserName(),login.getPassword()},new BeanPropertyRowMapper<Login>(Login.class));  
				  if(l.getUserName()!=null)
				    l.setRole("customer");
			}catch (EmptyResultDataAccessException e1) {
				l=null;
			}
		   
		    
		
		        }
				return l;
	}
	public Map<String,List<Product>> getProducts(){

		//String sql1="select * from GROCERY_TABLE "; 
		
		
		List <Product> groceryList= getGroceries(); 
		List <Product> sodaList= getSoda();  
		List <Product> smokesList= getSmokes();  

		/*String sql2="select * from SODA_TABLE "; 
		//List <Product> sodaList= new ArrayList<Product>();
		//sodaList= jdbcTemplate.queryForList(sql2, Product.class); 
		List <Product> sodaList= jdbcTemplate.query(sql2,new BeanPropertyRowMapper<Product>(Product.class));  

		String sql3="select * from SMOKES_TABLE "; 
		
		
		//List <Product> smokesList= jdbcTemplate.queryForList(sql3, Product.class);
		List <Product> smokesList= jdbcTemplate.query(sql3,new BeanPropertyRowMapper<Product>(Product.class));  
*/
		Map<String,List<Product>> products=new HashMap<String,List<Product>>();
		
		products.put("grocery", groceryList);
		products.put("soda", sodaList);
		products.put("smokes", smokesList);
		
		return products;
	}
	private List<Product> getSmokes() {
		return jdbcTemplate.query("select * from SMOKES_TABLE ",new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException{  
	        	Product product=new Product();  
	        	product.setProductID(rs.getString(1));  
	        	product.setProductName((rs.getString(2)));  
	        	product.setProductQuantity((rs.getInt(3)));  
	           
	            return product;  
	        }  
	    });
	}
	
	private List<Product> getSoda() {
		return jdbcTemplate.query("select * from SODA_TABLE ",new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException{  
	        	Product product=new Product();  
	        	product.setProductID(rs.getString(1));  
	        	product.setProductName((rs.getString(2)));  
	        	product.setProductQuantity((rs.getInt(3)));  
	           
	            return product;  
	        }  
	    });
	}
	
	public List<Product> getGroceries(){
		return jdbcTemplate.query("select * from GROCERY_TABLE ",new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException{  
	        	Product product=new Product();  
	        	product.setProductID(rs.getString(1));  
	        	product.setProductName((rs.getString(2)));  
	        	product.setProductQuantity((rs.getInt(3)));  
	           
	            return product;  
	        }  
	    });
	}
	

}
