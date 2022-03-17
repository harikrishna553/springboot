package com.sample.app.controller;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

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

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Operation(summary = "Get the employee details by name", parameters = {
			@Parameter(in = QUERY, name = "firstName", required = true, example = "krishna"),
			@Parameter(in = QUERY, name = "lastName", required = true, example = "krishna") })
	@GetMapping("/by-name")
	public ResponseEntity<Map<String, Object>> infoByName(
			@RequestParam(name = "firstName", required = false) String firstName,
			@RequestParam(name = "lastName", required = false) String lastName) {

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

