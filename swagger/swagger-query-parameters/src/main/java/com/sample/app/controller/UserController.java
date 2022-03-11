package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/users")
@Api(tags = { "user" })
@CrossOrigin("*")
public class UserController {

	@ApiOperation(value = "Get user details by either firstName or lastName", notes = "This API will get information of user by first or lastName")
	@GetMapping("/by-name")
	public ResponseEntity<Map<String, Object>> infoByName(
			@ApiParam(name = "firstName", value = "firstName ex: krishna", required = false) @RequestParam(name = "firstName", required = false) String firstName,
			@ApiParam(name = "lastName", value = "lastName ex: Krishna", required = false) @RequestParam(name = "lastName", required = false) String lastName) {

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

