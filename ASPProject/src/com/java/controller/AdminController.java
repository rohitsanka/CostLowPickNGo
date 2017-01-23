package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.bean.Login;
import com.java.bean.Role;

import com.java.bean.Person;
import com.java.service.AdminService;

@Controller  
@RequestMapping("/adminController")
public class AdminController {
	@Autowired
    AdminService adminService;
    @RequestMapping("/addCustomer")  
    public ModelAndView addCustomer(){

	 ModelAndView modelandview= new ModelAndView("addcustomer","addCustomer",new Person());
	 return modelandview;
}
    @RequestMapping("/addEmployee")  
    public ModelAndView addEmployee(){

	 ModelAndView modelandview= new ModelAndView("addemployee","addEmployee",new Person());
	 return modelandview;
}
	 
	 @RequestMapping("/addCustomerDb") 
	    public String addCustomer(@ModelAttribute("addCustomer") Person person) {
		 System.out.println(person.getFirstName());
		 adminService.addCustomer(person);
		
			return "adminsuccess";
	 
	 }

	 @RequestMapping("/addEmployeeDb") 
	    public String addEmployee(@ModelAttribute("addEmployee") Person person) {
		 System.out.println(person.getRole());
		 adminService.addEmployee(person);
		
			return "adminsuccess";
	 
	 }
	 @ModelAttribute("allRoles")
	 public List<Role> getAllRoles() 
	 {
	     ArrayList<Role> roles = new ArrayList<Role>();
	     roles.add(new Role(-1,  "Select Department"));
	     roles.add(new Role(1,  "Manager"));
	     roles.add(new Role(2,  "Representative"));
	     return roles;
	 }
	 @RequestMapping("/logout") 
	    public String logout() {
		
			return "Login";
	 
	 }

}