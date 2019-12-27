package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value = { "/", "/hello", "/welcome" })
	public String home() {
		return "Hello World";
	}
}