package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bean.Order;
import com.java.bean.WorkFlow;
import com.java.dao.RepresentativeDAO;

@Service
public class RepresentativeService {
	@Autowired
	RepresentativeDAO representativeDAO;

	public List<WorkFlow> getWorkFlow(String name) {
		// TODO Auto-generated method stub
		return representativeDAO.getWorkFlow(name);
	}

	public List<Order> getOrderDetails(String orderId) {
		// TODO Auto-generated method stub
		return representativeDAO.getOrderDetails(orderId);
	}

	public int completeWorkFlow(String orderId) {
		// TODO Auto-generated method stub
		
		return representativeDAO.completeWorkFlow(orderId);
	}

	public List<WorkFlow> getClosedOrders(String name) {
		// TODO Auto-generated method stub
		return representativeDAO.getClosedOrders(name);
	}
}
