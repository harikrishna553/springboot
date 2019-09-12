package com.sample.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to Spring Security";
	}

	@RequestMapping("/admin")
	@PreAuthorize("hasPermission(returnObject, 'role_admin')")
	public String welcomeAdmin() {
		return "Welcome Admin";
	}

	@RequestMapping("/user")
	@PreAuthorize("hasPermission(returnObject, 'role_user')")
	public String welcomeUser() {
		return "Welcome User";
	}

	@RequestMapping("/support")
	@PreAuthorize("hasPermission(returnObject, 'role_support')")
	public String welcomeSupportUser() {
		return "Welcome Support User";
	}

}
