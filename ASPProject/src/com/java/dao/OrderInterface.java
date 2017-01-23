package com.java.dao;

import java.util.List;

import com.java.bean.Order;
import com.java.bean.Product;

public interface OrderInterface {
	public void processGroceries(List<Product> selectedGroceryList);

	public void processSoda(List<Product> selectedSodaList);

	public void processSmokes(List<Product> selectedSmokesList);

	public Order getOrder(String name);

}
