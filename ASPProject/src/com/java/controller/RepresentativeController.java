package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.java.bean.Login;
import com.java.bean.Order;
import com.java.bean.WorkFlow;
import com.java.service.RepresentativeService;

@Controller  
@SessionAttributes("name")
@RequestMapping("/representativeController")
public class RepresentativeController {
	@Autowired
	RepresentativeService representativeService;
	
	@RequestMapping("/representativePage")  
	 public String viewRepresentativePage(Model model){
		
		Login login1= (Login) model.asMap().get("login");
		model.addAttribute("name", login1.getUserName());


		return"representativepage";
		
	}
	
	@RequestMapping("/viewWorkFlow")  
	 public String viewWorkFlow(@ModelAttribute("name") String name,Model model){
		
		List<WorkFlow> workFlowItems=representativeService.getWorkFlow(name);
		model.addAttribute("workFlowItems", workFlowItems);
		return"representativeworkflows";
		
	}
	
	@RequestMapping("/viewClosedOrders")  
	 public String viewClosedOrders(@ModelAttribute("name") String name,Model model){
		
		List<WorkFlow> closedOrder=representativeService.getClosedOrders(name);
		
		model.addAttribute("closedOrder", closedOrder);
		return"representativeclosedorders";
		
	}
	
	@RequestMapping(value="/viewOrder/{orderId}")  
	 public String viewOrder(@PathVariable String orderId,Model model){
		List<Order>  orderList= representativeService.getOrderDetails(orderId);
		model.addAttribute("orderList", orderList);
		return "representativevieworder";
		
	 }
	
	@RequestMapping(value="/completeWorkFlow/{orderId}")  
	 public String completeWorkFlow(@PathVariable String orderId,Model model){
		int  closed= representativeService.completeWorkFlow(orderId);
		if(closed>0)
			return "adminsuccess";
		return null;
		
	 }
}
