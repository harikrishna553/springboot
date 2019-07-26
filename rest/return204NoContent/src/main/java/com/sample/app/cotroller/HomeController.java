package com.sample.app.cotroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot REST App development";
	}

	@RequestMapping("/welcome")
	public Object welcomeMe(@RequestParam(name = "userName") String name) {

		if (name == null || "none".equalsIgnoreCase(name)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok("Hello " + name);

	}

}
