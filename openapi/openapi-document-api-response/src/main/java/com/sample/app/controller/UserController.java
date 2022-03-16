package com.sample.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.User;
import com.sample.app.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/users")
@Tag(name = "user", description = "User REST APIs")
@CrossOrigin("*")
public class UserController {

	@GetMapping("/by-id/{id}")
	@Operation(summary = "Get the user by first or lastName", description = "Get the user by first or lastName")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return the user details", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = "User not found for the given id", content = @Content) })

	public ResponseEntity<User> infoByName(
			@Parameter(name = "id", in = ParameterIn.PATH, required = true) @PathVariable(name = "id", required = true) Integer id) {

		if (!UserRepository.getUSERS_CACHE().containsKey(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(UserRepository.getUSERS_CACHE().get(id));
	}

}
