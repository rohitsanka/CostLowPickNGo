package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.bean.Order;
import com.java.bean.WorkFlow;
import com.java.email.EmailNotification;

@Repository
public class RepresentativeDAO {
	JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<WorkFlow> getWorkFlow(String name) {
		
		return jdbcTemplate.query("select  * from workflow WHERE representative_name='"+name+"' and order_status='processing'",new RowMapper<WorkFlow>(){  
	        public WorkFlow mapRow(ResultSet rs, int row) throws SQLException{  
	        	WorkFlow workFlow=new WorkFlow();  
	        	
	        	workFlow.setOrderId(rs.getString(1));  
	        	workFlow.setManagerName((rs.getString(2)));  
	        	workFlow.setRepresentativename(rs.getString(3));
	        
	            return workFlow;  
	        }  
	    });
	}
	public List<Order> getOrderDetails(String orderId) {
		return jdbcTemplate.query("select  *from ORDER_TABLE WHERE ORDER_ID='"+orderId+"'",new RowMapper<Order>(){  
	        public Order mapRow(ResultSet rs, int row) throws SQLException{  
	        	Order order=new Order();  
	        	
	        	order.setOrderId(rs.getString(1));  
	        	order.setCustomerName((rs.getString(6)));  
	        	order.setProductName(rs.getString(2));  
	        	order.setProductQuantity(rs.getInt(3));

	        	
	            return order;  
	        }  
	    });	}
	public int completeWorkFlow(String orderId) {
		String customerMailId=null;
		String updateOrder = "UPDATE order_table SET order_status = ? WHERE order_id = ?";
         
         Object[] params1 = { "closed", orderId};
         int[] types1 = {Types.VARCHAR, Types.VARCHAR};
        int update1=jdbcTemplate.update(updateOrder, params1, types1);
        
        String updateOrder2 = "UPDATE workflow SET order_status = ? WHERE order_id = ?";
        
        Object[] params2 = { "closed", orderId};
        int[] types2 = {Types.VARCHAR, Types.VARCHAR};
       int update2=jdbcTemplate.update(updateOrder2, params2, types2);
       if(update1>0 && update2>0)
    	   try{
   			String query="select c.email from customer_table c,order_table o where o.customer_name=c.username and o.order_id=?";
   			customerMailId= jdbcTemplate.queryForObject(query, new Object[]{orderId},new BeanPropertyRowMapper<String>(String.class));  
   			}
   			catch (Exception e) {
   		           System.err.println(e);
   		        }
       List<Order> emailOrderDetails=getOrderDetails(orderId);
   		String message = "<html> <body><h1>Hello </h1>";
   		
   	        String data;
   		message+="<table style=border:1px solid black;border-collapse:collapse; > <tr><th>Course</th><th>Faculty</th><th>Schedule</th></tr>";
   				for(Order order:emailOrderDetails){
   					data="<tr><td>"+order.getOrderId()+"</td>  <td>"+order.getProductName()+"</td>   <td>"+order.getProductQuantity()+"</td>  </tr>";
   					message=message+data;
   				}
   				message+=" </body></html>";
   				
   	     
   		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
   		
   		EmailNotification m=(EmailNotification)appContext.getBean("emailBean"); 
   	      
   	        m.sendMail("sreejap19@gmail.com",message);  
   	        ((ClassPathXmlApplicationContext) appContext).close();
   	   
        return update1;
      	}
	public List<WorkFlow> getClosedOrders(String name) {
		return jdbcTemplate.query("select  * from workflow WHERE representative_name='"+name+"' and order_status='closed'",new RowMapper<WorkFlow>(){  
	        public WorkFlow mapRow(ResultSet rs, int row) throws SQLException{  
	        	WorkFlow workFlow=new WorkFlow();  
	        	
	        	workFlow.setOrderId(rs.getString(1));  
	        	workFlow.setManagerName((rs.getString(2)));  
	        	workFlow.setRepresentativename(rs.getString(3));
	        
	            return workFlow;  
	        }  
	    });
	}
	
}
