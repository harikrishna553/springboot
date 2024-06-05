package com.sample.app.controller;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RedirectController {

	@GetMapping("/redirect")
	public ResponseEntity<Void> redirect() {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("https://self-learning-java-tutorial.blogspot.com/"));

		// Set Cache-Control header to cache the response for 1 hour (3600 seconds)
		headers.setCacheControl("max-age=3600, public");

		// Set Expires header to 1 hour from now
		ZonedDateTime expiresAt = ZonedDateTime.now().plusHours(1);
		String expires = DateTimeFormatter.RFC_1123_DATE_TIME.format(expiresAt);
		headers.set("Expires", expires);

		return new ResponseEntity<>(headers, HttpStatus.FOUND); // Use HttpStatus.FOUND (302) for temporary redirect
	}
}