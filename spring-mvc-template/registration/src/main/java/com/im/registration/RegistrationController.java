package com.im.registration;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.im.registration.entity.Registration;
import com.im.registration.persistance.RegistrationPersistance;

@Controller
public class RegistrationController {
	@Resource
	RegistrationPersistance registrationPersistance;
	
	 @RequestMapping(value="/registartion" ,method = RequestMethod.POST)
	    public String processRegistration(@ModelAttribute("registrationForm") Registration registration,
	            Map<String, Object> model) {
	         
	        // implement your own registration logic here...
	         System.out.println("  ssdsdddsd "  +  registration.getName());
	         
	         registrationPersistance.create(registration);
	         
	       
	         List<Registration> findAll = registrationPersistance.findAll();
	         for (Registration registration2 : findAll) {
				System.out.println(registration2.getName());
			}
	         model.put("list", findAll);
	         
	        return "registrationList";
	    }

}
