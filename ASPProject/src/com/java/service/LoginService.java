package com.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bean.Login;
import com.java.bean.Product;
import com.java.dao.LoginDAO;
import com.java.dao.LoginInterface;
import com.java.dao.OrderDAO;
@Service
public class LoginService {
@Autowired
LoginDAO loginDAO;

public Login userLogin(Login login) {
		
		return loginDAO.userLogin(login);
	}
public Map<String, List<Product>> getProducts() {
     Map<String,List<Product>> productsList=loginDAO.getProducts();
     return productsList;


}


}
