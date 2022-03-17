package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Operation(summary = "about me", description = "Details about myseld", parameters = {
			@Parameter(name = "authorization1", in = ParameterIn.HEADER, description = "Bearer token", required = true, examples = {
					@ExampleObject(name = "example1", value = "Bearer 1233rada"),
					@ExampleObject(name = "example2", value = "Bearer ASDSFA") }),
			@Parameter(name = "infoLevel", in = ParameterIn.HEADER, description = "info level", required = true, schema = @Schema(type = "string", allowableValues = {
					"ONLY_ID", "ONLYE_NAME_AND_ID", "ALL" })) })
	@GetMapping("/about-me")
	public ResponseEntity<Map<String, Object>> aboutMe() {
		Map<String, Object> myDetails = new HashMap<>();
		myDetails.put("id", 1);
		myDetails.put("name", "Krishna");

		return ResponseEntity.ok(myDetails);

	}

}
