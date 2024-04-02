package com.openclassrooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping("/hello")
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}

//	@GetMapping("/user")
//	public String getUser() {
//		return "Welcome, User";
//	}

//	@GetMapping("/admin")
//	public String getAdmin() {
//		return "Welcome, Admin";
//	}

}