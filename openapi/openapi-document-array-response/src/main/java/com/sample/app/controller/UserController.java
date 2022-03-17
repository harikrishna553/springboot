package com.sample.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Operation(summary = "Create new Employee", responses = {
		    @ApiResponse(responseCode = "200", description = "OK", content = {
		            @Content(
		                mediaType = "application/json",
		                array = @ArraySchema(schema = @Schema(implementation = EmployeeCreateRequestDto.class)),
		                examples = {
		                    @ExampleObject(name = "Example 1", summary = "Summary 1", description = "Some desc", 
		                    	value = "[{\"firstName\":\"Ram\",\"lastName\":\"Gurram\"},{\"firstName\":\"Krishna\",\"lastName\":\"N\"}]")
		                }
		            )
		        })
		    })
	@PostMapping
	public ResponseEntity<List<EmployeeCreateRequestDto>> createEmployee(
			@RequestBody(description = "Create new user", required = true, 
			content = @Content(mediaType = "application/json",
				array = @ArraySchema(schema = @Schema(implementation = EmployeeCreateRequestDto.class)),
				examples = {
	                    @ExampleObject(name = "Example 1", summary = "Summary 1", description = "sample payload", 
	                    	value = "[{\"firstName\":\"Ram\",\"lastName\":\"Gurram\"},{\"firstName\":\"Krishna\",\"lastName\":\"N\"}]")
	                }))
			@org.springframework.web.bind.annotation.RequestBody List<EmployeeCreateRequestDto> dtos) {

		return new ResponseEntity<>(dtos, HttpStatus.CREATED);

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

