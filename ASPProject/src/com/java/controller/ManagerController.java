package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.bean.Login;
import com.java.bean.Order;
import com.java.bean.Product;
import com.java.bean.WorkFlow;
import com.java.service.ManagerService;

@Controller  
@SessionAttributes("name")


@RequestMapping("/managerController")
public class ManagerController {
	@Autowired
	ManagerService mangerService;

	@RequestMapping("/managerPage")  
	 public String viewMangerPage(Model model){
		
		Login login1= (Login) model.asMap().get("login");
		model.addAttribute("name", login1.getUserName());


		return"managerpage";
		
	}

	
	@RequestMapping("/viewOpenOrders")  
	 public String viewOpenOrder(Model model){
		List<Order>  openOrderList= mangerService.getOpenOrders();
		model.addAttribute("openOrderList", openOrderList);
		return "manageropenorder";
		
	 }
	
	@RequestMapping("/viewProcessingOrders")  
	 public String viewProcessingOrder(Model model){
		List<WorkFlow>  processingOrderList= mangerService.getProcessingOrders();
		model.addAttribute("processingOrderList", processingOrderList);
		return "managerviewprocessingorders";
		
	 }
	
	@RequestMapping("/viewClosedOrders")  
	 public String viewClosedOrder(Model model){
		List<WorkFlow>  processingOrderList= mangerService.getClosedOrders();
		model.addAttribute("processingOrderList", processingOrderList);
		return "managerviewclosedorders";
		
	 }
	
	@RequestMapping("/viewRepresentativePerformance")  
	 public String viewRepresentativePerformance(Model model){
		List<WorkFlow>  representativePerformance= mangerService.getRepresentativePerformance();
		model.addAttribute("representativePerformance", representativePerformance);
		return "managerviewrepresentativeperformance";
		
	 }
	
	
	@RequestMapping(value="/viewOrder/{orderId}")  
	 public String viewOrder(@PathVariable String orderId,Model model){
		List<Order>  orderList= mangerService.getOrderDetails(orderId);
		model.addAttribute("orderList", orderList);
		return "managervieworder";
		
	 }
	
	@RequestMapping(value="/assignWorkFlow/{orderId}")  
	 public ModelAndView assignWorkFlow(@PathVariable String orderId,@ModelAttribute("name") String name){
		List<String>  representativeList= mangerService.getRepresentatives();
		ModelAndView modelandview = new ModelAndView("managerworkflow", "WorkFlow", new WorkFlow());
		
	    modelandview.addObject("representativeList", representativeList);
		modelandview.addObject("orderId", orderId);
		modelandview.addObject("managerName", name);

		
		return modelandview;
		
	 }
	
	
	@RequestMapping(value="/processWorkFlow")  
	 public String processWorkFlow(@ModelAttribute("WorkFlow") WorkFlow workFlow){
		int processed=mangerService.processWorkFlow(workFlow);
		if(processed>0){
			return "adminsuccess";
		}
	   

		
		return null;
		
	 }
	

}
