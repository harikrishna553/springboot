package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.config.EnvironmentConfig;

@RestController
public class HomeController {
	
	@Autowired
	EnvironmentConfig envConfig;

	@RequestMapping("/")
	public String homePage() {
		return "Welcome to Spring boot Application Development";
	}
	
	@RequestMapping("/config")
	public String configValues() {
		return envConfig.toString();
	}

}