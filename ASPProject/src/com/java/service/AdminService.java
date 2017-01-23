package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.bean.Person;
import com.java.dao.AdminDAO;
@Service
public class AdminService {
	@Autowired
	AdminDAO adminDAO;

	public void addCustomer(Person person) {
		// TODO Auto-generated method stub
		adminDAO.addCustomer(person);
	}

	public void addEmployee(Person person) {
		// TODO Auto-generated method stub
		adminDAO.addEmployee( person);
		
	}

}
