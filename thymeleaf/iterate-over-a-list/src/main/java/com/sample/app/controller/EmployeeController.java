package com.sample.app.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.app.model.Employee;

@Controller
public class EmployeeController {

	@GetMapping("/emps")
	public ModelAndView emps() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employees");

		Employee emp1 = new Employee(1, "Ram");
		Employee emp2 = new Employee(2, "Krishna");
		Employee emp3 = new Employee(3, "Rahim");

		modelAndView.addObject("emps", Arrays.asList(emp1, emp2, emp3));

		return modelAndView;
	}
}
