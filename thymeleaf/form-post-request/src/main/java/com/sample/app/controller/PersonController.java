package com.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.app.model.Person;

@Controller
public class PersonController {

	@GetMapping("/register-me")
	public ModelAndView emps() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("registerMe");
		
		Person person = new Person();
		modelAndView.addObject("person", person);

		return modelAndView;
	}
	
	@PostMapping("/persons")
	public ModelAndView saveEmployee(@ModelAttribute Person person) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employeeInfo");
		
		modelAndView.addObject("person", person);

		return modelAndView;
	}
}
