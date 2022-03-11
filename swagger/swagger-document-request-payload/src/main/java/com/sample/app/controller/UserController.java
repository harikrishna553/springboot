package com.sample.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@ApiOperation(value = "Create user", notes = "Onboard new user")
	@PostMapping
	public ResponseEntity<EmployeeCreateRequestDto> infoByName(
			@ApiParam(name = "employeePaylod", required = true) @RequestBody EmployeeCreateRequestDto dto) {

		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	private static class EmployeeCreateRequestDto {
		private String firstName;
		private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	}

}