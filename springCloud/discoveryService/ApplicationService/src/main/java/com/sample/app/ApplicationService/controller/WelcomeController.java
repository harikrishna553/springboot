package com.sample.app.ApplicationService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot developement";
	}
}
