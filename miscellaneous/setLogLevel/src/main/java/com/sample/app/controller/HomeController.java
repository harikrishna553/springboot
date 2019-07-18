package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HomeController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String homePage() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");
		
		return "Welcome to Spring boot Application Development";
	}

}