package com.sample.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class HelloController {
	
	private final Counter helloCounter;
	
	public HelloController(MeterRegistry meterRegistry) {
		helloCounter = Counter.builder("api.hello").register(meterRegistry);
	}

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot developement";
	}
	
	@RequestMapping("/hello")
	public String hello() {
		helloCounter.increment();
		return "Good Morning";
	}

}
