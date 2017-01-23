package com.java.controller;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
  
@Controller  
@RequestMapping("/welcomeController")  
public class WelcomeWorldController {  
  
    @RequestMapping("/welcome")  
    public ModelAndView helloWorld() {  
  
        String message = "WELCOME SPRING MVC";  
        return new ModelAndView("welcomepage", "message", message);  
    }  
      
}