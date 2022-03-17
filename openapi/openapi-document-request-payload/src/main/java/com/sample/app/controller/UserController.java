package com.sample.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Operation(summary = "Create new Employee")
	@PostMapping
	public ResponseEntity<EmployeeCreateRequestDto> createEmployee(
			@RequestBody(description = "Create new user", required = true, 
			content = @Content(schema = @Schema(implementation = EmployeeCreateRequestDto.class))) 
			@org.springframework.web.bind.annotation.RequestBody EmployeeCreateRequestDto dto) {

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
