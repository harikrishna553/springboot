package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@GetMapping("/by-city/{city}")
	@Operation(summary = "My endpoint", security = { @SecurityRequirement(name = "bearerAuth") })
	public ResponseEntity<Map<String, Object>> infoByCity(
			@Parameter(name = "city", description = "city ex: Bangalore") @PathVariable(name = "city") String city) {

		Map<String, Object> result = new HashMap<>();

		return ResponseEntity.ok(result);

	}

	@GetMapping("/say-hello")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Good Morning!!!!");

	}

}