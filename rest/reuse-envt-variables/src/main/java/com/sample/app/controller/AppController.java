package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Value("${app.description}")
	private String appDescription;

	@GetMapping("/aboutMe")
	public String getDescription() {
		return appDescription;
	}

}
