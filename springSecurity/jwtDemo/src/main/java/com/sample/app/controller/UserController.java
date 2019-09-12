package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.User;
import com.sample.app.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user/")
@Api(tags = "users")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	@ApiOperation(value = "User login API")
	@ApiResponses(value = { @ApiResponse(code = 422, message = "Invalid username/password supplied"),
			@ApiResponse(code = 500, message = "Something went wrong") })
	public String login(@ApiParam @RequestParam(name = "userName") String username,
			@ApiParam @RequestParam(name = "password") String password) {
		return userService.signin(username, password);
	}

	@RequestMapping(value = "aboutMe", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "SSO token in format: \n\rBearer eyadgldkZDA12y", dataType = "String", paramType = "header", required = true) })
	public User aboutMe(@ApiParam("Enter User Name") @RequestParam String username) {
		return userService.findByName(username);
	}

}
