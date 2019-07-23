package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot developement";
	}
	
	@RequestMapping("/greetMe")
	public String greetMe() {
		throw new NumberFormatException("Input can't be converted to number");
	}
	
	@RequestMapping("/hello")
	public String sayHello() {
		throw new RuntimeException("Run time Exception Occured");
	}
	
}
