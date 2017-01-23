package com.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.bean.Order;
import com.java.bean.Product;
import com.java.bean.WorkFlow;

@Repository

public class ManagerDAO {
	JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<Order> getopenOrders() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select distinct ORDER_ID,CUSTOMER_NAME from ORDER_TABLE WHERE ORDER_STATUS='open' ",new RowMapper<Order>(){  
	        public Order mapRow(ResultSet rs, int row) throws SQLException{  
	        	Order order=new Order();  
	        	
	        	order.setOrderId(rs.getString(1));  
	        	order.setCustomerName((rs.getString(2)));  
	            return order;  
	        }  
	    });
	}
	public List<Order> getOrderDetails(String orderId) {
		// TODO Auto-generated method stub
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
	public List<String> getRepresentatives() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select  USERNAME from EMPLOYEE_TABLE WHERE ROLE='2'",new RowMapper<String>(){  
	        public String mapRow(ResultSet rs, int row) throws SQLException{  
	

	        	//List<String>represntativeList=new ArrayList<String>();
	        	return rs.getString(1);
	        	
	           // return null;  
	        }  
	    });
		}
	public int processWorkFlow(WorkFlow workFlow) {
		String sql1= "INSERT INTO WORKFLOW (" +"order_id,"+"manager_name,"+"representative_NAME,"+"order_status)"+"VALUES(?,?,?,?)";
		 Object[] params = new Object[] { workFlow.getOrderId(), workFlow.getManagerName(), workFlow.getRepresentativename(),"processing"};
         int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
         int inserted = jdbcTemplate.update(sql1, params, types);
         
         String updateOrder = "UPDATE order_table SET order_status = ? WHERE order_id = ?";
         
         Object[] params1 = { "processing", workFlow.getOrderId()};
         int[] types1 = {Types.VARCHAR, Types.VARCHAR};
        jdbcTemplate.update(updateOrder, params1, types1);
        
		return inserted;
	}
	public List<WorkFlow> getProcessingOrders() {
		
		return jdbcTemplate.query("select * from WORKFLOW WHERE ORDER_STATUS='processing' ",new RowMapper<WorkFlow>(){  
	        public WorkFlow mapRow(ResultSet rs, int row) throws SQLException{  
	        	WorkFlow workFlow=new WorkFlow();  
	        	
	        	workFlow.setOrderId(rs.getString(1));  
	        	workFlow.setRepresentativename((rs.getString(3)));  
	            return workFlow;  
	        }  
	    });
	}
	public List<WorkFlow> getRepresentativePerformance() {
		return jdbcTemplate.query("SELECT COUNT(REPRESENTATIVE_NAME), REPRESENTATIVE_NAME  FROM WORKFLOW where ORDER_STATUS='closed' group by REPRESENTATIVE_NAME ",new RowMapper<WorkFlow>(){  
	        public WorkFlow mapRow(ResultSet rs, int row) throws SQLException{  
	        	WorkFlow workFlow=new WorkFlow();  
	        	
	        	workFlow.setNoOfOrdersForRepresentative(rs.getString(1));  
	        	workFlow.setRepresentativename((rs.getString(2)));  
	            return workFlow;  
	        }  
	    });
}
	public List<WorkFlow> getClosedOrders() {
		return jdbcTemplate.query("select * from WORKFLOW WHERE ORDER_STATUS='closed' ",new RowMapper<WorkFlow>(){  
	        public WorkFlow mapRow(ResultSet rs, int row) throws SQLException{  
	        	WorkFlow workFlow=new WorkFlow();  
	        	
	        	workFlow.setOrderId(rs.getString(1));  
	        	workFlow.setRepresentativename((rs.getString(3)));  
	            return workFlow;  
	        }  
	    });
	}
}