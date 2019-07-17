package com.sample.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees/")
public class EmployeeController {

	
	@RequestMapping(value = "registered/count", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String countEmps() {
		return "Total Registered Employees : "+  1024;
	}
	
	@RequestMapping(value = "greetMe", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String greetMe() {
		return "Very Good Day to you";
	}
}
