package com.sample.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/")
public class WelcomeController {

	@GetMapping("say-hi")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hi!!!!!!!!!!!");
	}
}
