package com.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.bean.Login;
import com.java.bean.Order;
import com.java.bean.Product;
import com.java.service.OrderService;
@Controller
@SessionAttributes("name")
@RequestMapping("/orderController")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	

	@RequestMapping("/processOrder")  
	 public String processOrder(@ModelAttribute Order order,Model model,@ModelAttribute("name") String name){
		
		 List<Product> selectedGroceryList=getSelectedGroceryList(order.getGroceryList());
		 List<Product> selectedSodaList=getSelectedGroceryList(order.getSodaList());
		 List<Product> selectedSmokesList=getSelectedGroceryList(order.getSmokesList());

		 if(selectedGroceryList!=null)
		 orderService.processGroceries(selectedGroceryList);
		 if(selectedSodaList!=null)
		 orderService.processSoda(selectedSodaList);
		 if(selectedSmokesList!=null)
		 orderService.processSmokes(selectedSmokesList);
		 Order orderDetails=orderService.getOrder(name);
		 //ModelAndView modelAndView=new ModelAndView();
 	     model.addAttribute("order", orderDetails);
		 
		 return "orderconformation";
	 }

	@RequestMapping("/displayOrder")  
	 public ModelAndView displayOrder(Model model){
		Login login1= (Login) model.asMap().get("login");
		
		@SuppressWarnings("unchecked")
		Map<String,List<Product>> productsList= (Map<String,List<Product>>) model.asMap().get("products");
		 List<Product> groceryList=productsList.get("grocery");
	       List<Product> sodaList=productsList.get("soda");
	       List<Product> smokesList=productsList.get("smokes");
	       ModelAndView modelAndView=new ModelAndView("customerpage","orderPage",new Order());
	       modelAndView.addObject("name", login1.getUserName());
	 	    modelAndView.addObject("grocery", groceryList);
	        modelAndView.addObject("soda", sodaList);
	        modelAndView.addObject("smokes", smokesList);
	        

	       return modelAndView ;  
		/* List<Product> selectedGroceryList=getSelectedGroceryList(order.getGroceryList());
		 List<Product> selectedSodaList=getSelectedGroceryList(order.getSodaList());
		 List<Product> selectedSmokesList=getSelectedGroceryList(order.getSmokesList());

		 if(selectedGroceryList!=null)
		 orderService.processGroceries(selectedGroceryList);
		 if(selectedSodaList!=null)
		 orderService.processSoda(selectedSodaList);
		 if(selectedSmokesList!=null)
		 orderService.processSmokes(selectedSmokesList);
		 Order orderDetails=orderService.getOrder(login.getUserName());
		 //ModelAndView modelAndView=new ModelAndView();
	     model.addAttribute("order", orderDetails);
		 */
		
	 }

	private List<Product> getSelectedGroceryList(List<Product> groceryList) {
		List<Product>selectedItems = new ArrayList<Product>();
		for(Product gList:groceryList){
			if(gList!=null){
			if(gList.getProductID()!=null&&gList.getProductQuantity()!=0){
				selectedItems.add(gList);
			}
			}
			
		}
		return selectedItems;
	}
	
	
	
}
