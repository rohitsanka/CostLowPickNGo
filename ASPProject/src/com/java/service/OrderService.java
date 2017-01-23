package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bean.Order;
import com.java.bean.Product;
import com.java.dao.OrderDAO;
import com.java.dao.OrderInterface;
@Service
public class OrderService {
	@Autowired
	OrderInterface orderDAO;
	public void processGroceries(List<Product> selectedGroceryList) {
		// TODO Auto-generated method stub
		orderDAO.processGroceries(selectedGroceryList);
		
	}
	public void processSoda(List<Product> selectedSodaList) {
		// TODO Auto-generated method stub
		orderDAO.processSoda(selectedSodaList);

		
	}
	public void processSmokes(List<Product> selectedSmokesList) {
		// TODO Auto-generated method stub
		orderDAO.processSmokes(selectedSmokesList);

		
	}
	public Order getOrder(String name) {
		// TODO Auto-generated method stub
		return orderDAO.getOrder(name);
	}
}
