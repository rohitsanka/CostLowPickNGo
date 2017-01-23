package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.ManagerDAO;
import com.java.bean.Order;
import com.java.bean.WorkFlow;

@Service


public class ManagerService {
	@Autowired
ManagerDAO managerDAO;

	public List getOpenOrders() {
		// TODO Auto-generated method stub
		
		List<Order> openOrderList=managerDAO.getopenOrders();
		return openOrderList;

	
	}

	public List<Order> getOrderDetails(String orderId) {
		// TODO Auto-generated method stub
		List<Order> orderList=managerDAO.getOrderDetails(orderId);

		return orderList;
	}

	public List<String> getRepresentatives() {
		// TODO Auto-generated method stub
		List<String> reperesntativeList=managerDAO.getRepresentatives();
		return reperesntativeList;
	}

	public int processWorkFlow(WorkFlow workFlow) {
		// TODO Auto-generated method stub
		return managerDAO.processWorkFlow(workFlow);
	}

	public List<WorkFlow> getProcessingOrders() {
		// TODO Auto-generated method stub
		return managerDAO.getProcessingOrders();
	}

	public List<WorkFlow> getRepresentativePerformance() {
		// TODO Auto-generated method stub
		return managerDAO.getRepresentativePerformance();
	}

	public List<WorkFlow> getClosedOrders() {
		// TODO Auto-generated method stub
		return managerDAO.getClosedOrders();
	}

}
