package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees/")
public class EmployeeController {

	@RequestMapping(value = "registered/count", method = RequestMethod.GET)
	public String countEmps() {
		return "Total Registered Employees : "+  1024;
	}
}
