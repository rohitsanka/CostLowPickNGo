package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.bean.Login;
import com.java.bean.Product;

public interface LoginInterface {
	public Login userLogin(Login login);

	public Map<String,List<Product>> getProducts();
	public void processGroceries(List<Product> selectedGroceryList);
}
