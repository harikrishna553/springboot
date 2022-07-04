package com.sample.app.controller;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@RestController
public class HelloController {

	private final Bucket bucket;

	public HelloController() {
		Bandwidth limit = Bandwidth.classic(5, Refill.greedy(2, Duration.ofSeconds(30)));
		bucket = Bucket.builder().addLimit(limit).build();
	}

	@RequestMapping("/hello")
	public ResponseEntity<String> home() {

		System.out.println("Availabel tokens : " + bucket.getAvailableTokens());

		if (bucket.tryConsume(1)) {
			return ResponseEntity.ok("Hello!!!!");
		}

		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).header("Retry-After", "10").build();

	}
}