package com.sample.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/users")
@Tag(name = "greet", description = "Applicaiton Welcome APIs")
@CrossOrigin("*")
public class WelcomeController {

	@Operation(summary = "Welcome user", description = "Greet user with a welcome message")
	@GetMapping("/welcome")
	public ResponseEntity<String> sayHi() {
		return ResponseEntity.ok("Welcome user!!!!!");

	}

}