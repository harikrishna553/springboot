package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/users")
@Tag(name = "user", description = "User REST APIs")
@CrossOrigin("*")
public class UserController {

	@GetMapping("/by-name")
	@Operation(summary="Get the user by first or lastName", description = "Get the user by first or lastName")
	public ResponseEntity<Map<String, Object>> infoByName(
			@Parameter(name = "firstName", in = ParameterIn.QUERY, description = "firstName ex: krishna", required = true) @RequestParam(name = "firstName", required = false) String firstName,
			@Parameter(name = "lastName", in = ParameterIn.QUERY, description = "lastName ex: krishna", required = true) @RequestParam(name = "lastName", required = false) String lastName) {

		Map<String, Object> myDetails = new HashMap<>();

		if (firstName != null) {
			myDetails.put("firstName", firstName);
		}

		if (lastName != null) {
			myDetails.put("lastName", lastName);
		}

		return ResponseEntity.ok(myDetails);

	}

}
