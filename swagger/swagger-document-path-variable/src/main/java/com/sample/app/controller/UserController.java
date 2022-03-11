package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1/users")
@Api(tags = { "user" })
@CrossOrigin("*")
public class UserController {

	@ApiOperation(value = "Get uses informaiton by city", notes = "This API will get information of user by city")
	@GetMapping("/by-city/{city}")
	public ResponseEntity<Map<String, Object>> infoByName(
			@ApiParam(name = "city", value = "city ex: Bangalore", required = true) @PathVariable(name = "city") String firstName) {

		Map<String, Object> result = new HashMap<>();

		return ResponseEntity.ok(result);

	}

}
