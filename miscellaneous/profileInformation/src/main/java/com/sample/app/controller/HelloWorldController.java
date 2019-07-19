package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private Environment environment;

	@RequestMapping("/")
	public String home() {
		return "Welcome to Spring boot Application development";
	}

	@RequestMapping("/profile")
	public String profile() {
		String[] activeProfiles = environment.getActiveProfiles();

		StringBuilder stringBuilder = new StringBuilder();

		for (String activeProfile : activeProfiles) {
			stringBuilder.append(activeProfile);
			stringBuilder.append("<br />");
		}
		return stringBuilder.toString();
	}
}
