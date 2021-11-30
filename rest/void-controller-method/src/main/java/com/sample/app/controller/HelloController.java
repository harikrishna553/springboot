package com.sample.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/health")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateDataThatDoesntRequireClientToBeNotified() {
		System.out.println("Request received to check application health");
	}
}