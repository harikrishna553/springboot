package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@GetMapping("/by-city/{city}")
	public ResponseEntity<Map<String, Object>> infoByCity(
			@Parameter(name = "city", description = "city ex: Bangalore", schema = @Schema(type = "string", allowableValues = {
					"Bangalore", "Chennai", "Hyderabad" })) @PathVariable(name = "city") String city) {

		Map<String, Object> result = new HashMap<>();

		return ResponseEntity.ok(result);

	}

}