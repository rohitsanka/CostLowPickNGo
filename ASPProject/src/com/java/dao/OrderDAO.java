package com.java.dao;

import java.sql.ResultSet;
import java.util.Random;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.bean.Order;
import com.java.bean.Product;

@Repository
public class OrderDAO implements OrderInterface {
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	Map<String,List<Product>> orderMap=new HashMap<String,List<Product>>();
	public static int number=1;
	
	

	public void processGroceries(List<Product> selectedGroceryList) {
	
		List<Product> orderGroceryList=new ArrayList<Product>();
		List<Product> product=new ArrayList<Product>();
		List<Product> groceryList=selectedGroceryList;
		//Product p;
		// TODO Auto-generated method stub
		for(Product list: selectedGroceryList){
			
			String sql1="select * from GROCERY_TABLE where PRODUCT_ID=? "; 
		   // p= jdbcTemplate.queryForObject(sql1, new Object[]{list.getProductID()},new BeanPropertyRowMapper<Product>(Product.class));  
			product=jdbcTemplate.query(sql1,new Object[]{list.getProductID()},new RowMapper<Product>(){  
		        public Product mapRow(ResultSet rs, int row) throws SQLException{  
		        	Product p=new Product();  
		        	p.setProductID(rs.getString(1));  
		        	p.setProductName((rs.getString(2)));  
		        	p.setProductQuantity((rs.getInt(3)));  
		           
		            return p;  
		        }  
		    });

			List<Product> orderList=updateQuantity(product,groceryList);
			List<Product> productList=getOrderList(product,groceryList);
			for(Product products:productList){
				orderGroceryList.add(products);
				
			}

			


			
			String sql="UPDATE GROCERY_TABLE SET PRODUCT_QUANTITY = ? WHERE PRODUCT_ID = ?";
			for(Product oList:orderList){
	        Object[] params = { oList.getProductQuantity(), oList.getProductID()};
	        int[] types = {Types.BIGINT, Types.VARCHAR};
	        int rows = jdbcTemplate.update(sql, params, types);
			}


		}
		orderMap.put("grocery",orderGroceryList);

	}
	

	public void processSoda(List<Product> selectedSodaList){
		List<Product> orderSodaList=new ArrayList<Product>();

		List<Product> product=new ArrayList<Product>();
		List<Product> sodaList=selectedSodaList;
		//Product p;
		// TODO Auto-generated method stub
		for(Product list: selectedSodaList){
			
			String sql1="select * from SODA_TABLE where PRODUCT_ID=? "; 
		   // p= jdbcTemplate.queryForObject(sql1, new Object[]{list.getProductID()},new BeanPropertyRowMapper<Product>(Product.class));  
			product=jdbcTemplate.query(sql1,new Object[]{list.getProductID()},new RowMapper<Product>(){  
		        public Product mapRow(ResultSet rs, int row) throws SQLException{  
		        	Product p=new Product();  
		        	p.setProductID(rs.getString(1));  
		        	p.setProductName((rs.getString(2)));  
		        	p.setProductQuantity((rs.getInt(3)));  
		           
		            return p;  
		        }  
		    });

			List<Product> orderList=updateQuantity(product,sodaList);
			List<Product> productList=getOrderList(product,sodaList);
			for(Product products:productList){
				orderSodaList.add(products);
				
			}

			
			System.out.println(orderList);
			String sql="UPDATE SODA_TABLE SET PRODUCT_QUANTITY = ? WHERE PRODUCT_ID = ?";
			for(Product oList:orderList){
	        Object[] params = { oList.getProductQuantity(), oList.getProductID()};
	        int[] types = {Types.BIGINT, Types.VARCHAR};
	        int rows = jdbcTemplate.update(sql, params, types);
			}


	
		}
		orderMap.put("soda", orderSodaList);

	}
	
	
	

	public void processSmokes(List<Product> selectedSmokesList){
		List<Product> orderSmokeList=new ArrayList<Product>();

		String sql1="select * from SMOKES_TABLE where PRODUCT_ID=? "; 

		List<Product> product=new ArrayList<Product>();
		List<Product> smokesList=selectedSmokesList;
		//Product p;
		// TODO Auto-generated method stub
		for(Product list: selectedSmokesList){
			
		   // p= jdbcTemplate.queryForObject(sql1, new Object[]{list.getProductID()},new BeanPropertyRowMapper<Product>(Product.class));  
			product=jdbcTemplate.query(sql1,new Object[]{list.getProductID()},new RowMapper<Product>(){  
		        public Product mapRow(ResultSet rs, int row) throws SQLException{  
		        	Product p=new Product();  
		        	p.setProductID(rs.getString(1));  
		        	p.setProductName((rs.getString(2)));  
		        	p.setProductQuantity((rs.getInt(3)));  
		           
		            return p;  
		        }  
		    });

			List<Product> orderList=updateQuantity(product,smokesList);
			List<Product> productList=getOrderList(product,smokesList);
			for(Product products:productList){
				orderSmokeList.add(products);
				
			}
			
			System.out.println(orderList);
			String sql="UPDATE SMOKES_TABLE SET PRODUCT_QUANTITY = ? WHERE PRODUCT_ID = ?";
			for(Product oList:orderList){
	        Object[] params = { oList.getProductQuantity(), oList.getProductID()};
	        int[] types = {Types.BIGINT, Types.VARCHAR};
	        int rows = jdbcTemplate.update(sql, params, types);
			}


		}
		orderMap.put("smokes", orderSmokeList);

		
	}
	
	
	
	private List<Product> updateQuantity(List<Product> product,List<Product> itemsList) {
		List<Product> updateList=new ArrayList<Product>(); 
		Product p=new Product();
		for(Product gList:itemsList)
		{
			for(Product pList:product){
				if(gList.getProductID().equals(pList.getProductID())){
					int quantity= pList.getProductQuantity()-gList.getProductQuantity();
					p.setProductID(pList.getProductID());
					p.setProductName(pList.getProductName());
					p.setProductQuantity(quantity);
					updateList.add(p);
		       }
				
			}
		}
		return updateList;
	}
	
	private List<Product> getOrderList(List<Product> product,List<Product> itemsList) {
		List<Product> updateList=new ArrayList<Product>(); 
		Product p=new Product();
		for(Product gList:itemsList)
		{
			for(Product pList:product){
				if(gList.getProductID().equals(pList.getProductID())){
					int quantity= gList.getProductQuantity();
					p.setProductID(pList.getProductID());
					p.setProductName(pList.getProductName());
					p.setProductQuantity(quantity);
					updateList.add(p);
		       }
				
			}
		}
		return updateList;
		
	}


	@Override
	public Order getOrder(String name) {
		// TODO Auto-generated method stub
		 String sql1= "INSERT INTO ORDER_TABLE (" +"ORDER_ID,"+"PRODUCT_NAME,"+"PRODUCT_QUANTITY,"+"ORDER_TYPE,"+"ORDER_STATUS,"+"CUSTOMER_NAME)"+"VALUES(?,?,?,?,?,?)";

		 Random random=new Random();
		 int num = random.nextInt(90000000) + 10000000;
		 Order order=new Order();

		 String orderID="ORD00"+num;
		 String orderStatus="open";
		 
	       List<Product> groceryList=orderMap.get("grocery");
 	       List<Product> sodaList=orderMap.get("soda");
 	       List<Product> smokesList=orderMap.get("smokes");
 	       if(groceryList!=null){
 	       for(Product gList:groceryList){
 	    	  String orderType="grocery";
 	          Object[] params = new Object[] { orderID, gList.getProductName(), gList.getProductQuantity(),orderType,orderStatus,name};
 	          int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
 	          int row = jdbcTemplate.update(sql1, params, types);


 	       }
 	       }
 	       if(sodaList!=null){
 	      for(Product gList:sodaList){
 	    	  String orderType="soda";
 	          Object[] params = new Object[] { orderID, gList.getProductName(), gList.getProductQuantity(),orderType,orderStatus,name};
 	          int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
 	          int row = jdbcTemplate.update(sql1, params, types);


 	       }
 	       }
 	       if(smokesList!=null){
 	       for(Product gList:smokesList){
 	    	   String orderType="smokes";
  	          Object[] params = new Object[] { orderID, gList.getProductName(), gList.getProductQuantity(),orderType,orderStatus,name};
  	          int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
  	          int row = jdbcTemplate.update(sql1, params, types);


  	       }


 	       }
 	       order.setOrderId(orderID);
 	       order.setOrderStatus(orderStatus);
 	       order.setGroceryList(groceryList);
 	       order.setSodaList(sodaList);
 	       order.setSmokesList(smokesList);
 	       
 	       groceryList=null;
 	       smokesList=null;
 	       sodaList=null;
 	       
		 
		number++;
		 return order;
	}

}
