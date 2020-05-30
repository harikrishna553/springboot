package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}
}
