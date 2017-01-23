package com.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.bean.Login;
import com.java.bean.Product;
import com.java.service.LoginService;

@Controller

// @RequestMapping("/loginController")
public class LoginController {
	@Autowired
	LoginService loginService;

	// @RequestMapping("/LoginForm")
	@RequestMapping("/")
	public ModelAndView loginPage() {
		ModelAndView modelandview = new ModelAndView("Login", "loginPage", new Login());
		return modelandview;
	}

	@RequestMapping("/login")
	public ModelAndView helloWorld(@ModelAttribute Login login, RedirectAttributes redirectAttributes) {

		String name = login.getUserName();
		Login user = loginService.userLogin(login);

		if (user != null) {

			if (user.getRole().equals("customer")) {

				redirectAttributes.addFlashAttribute("login", login);
				Map<String, List<Product>> productsList = loginService.getProducts();

				redirectAttributes.addFlashAttribute("products", productsList);

				String page = "redirect:/orderController/displayOrder";
				/*
				 * String message = "HELLO customer "+name; List<Product>
				 * groceryList=productsList.get("grocery"); List<Product>
				 * sodaList=productsList.get("soda"); List<Product>
				 * smokesList=productsList.get("smokes");
				 */
				// ModelAndView modelAndView=new
				// ModelAndView("customerpage","orderPage",new Order());
				ModelAndView modelAndView = new ModelAndView(page);
				// modelAndView.addObject("message", message);
				// modelAndView.addObject("grocery", groceryList);
				// modelAndView.addObject("soda", sodaList);
				// modelAndView.addObject("smokes", smokesList);

				return modelAndView;

			} else if (user.getRole().equals("1")) {
				redirectAttributes.addFlashAttribute("login", login);
				String page = "redirect:/managerController/managerPage";
				ModelAndView modelAndView = new ModelAndView(page);


				return modelAndView;

			} else if (user.getRole().equals("admin")) {
				String message = "HELLO admin " + name;

				return new ModelAndView("adminpage", "message", message);

			} else if (user.getRole().equals("2")) {
				
				redirectAttributes.addFlashAttribute("login", login);
				String page = "redirect:/representativeController/representativePage";
				ModelAndView modelAndView = new ModelAndView(page);

				return modelAndView;

			}
		}
		return new ModelAndView("errorpage", "message", "Sorry, username or password error");

	}

}