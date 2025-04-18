package com.sample.app.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Timed(value = "hello.request", description = "Time taken to return hello")
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Prometheus!";
	}
}
