package com.sample.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home() {

		LOGGER.debug("Debug message");
		LOGGER.info("Information message");
		LOGGER.warn("Warning message");
		LOGGER.error("Error message");

		return "Hello World";
	}
}