package com.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	/**
	 * 
	 * @return the name of template
	 */
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}
}
