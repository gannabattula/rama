package com.im.registration;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.im.registration.entity.Registration;

@Controller
public class LoginController {

	
	 @RequestMapping("/login")
	   public String hello(Map<String, Object> model) {
	        
	        
	       Registration registrationForm = new Registration();    
	        model.put("registrationForm", registrationForm);
	        
	       return "registration";
	        
	   }
	
}
