package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.UserDto;
import com.sample.app.entity.User;
import com.sample.app.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "This section contains all user Speicifc Operations" })
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create new user", notes = "Create new user")
	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveEmployee(@RequestBody UserDto empDto) {

		User persistedEmp = userService.save(empDto);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get user", notes = "Get user by id")
	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getEmployees(@PathVariable final Integer id) {

		User persistedEmp = userService.getById(id);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

}
