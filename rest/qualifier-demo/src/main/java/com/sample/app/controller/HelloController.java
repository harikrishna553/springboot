package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.service.HelloService;

@RestController
public class HelloController {

	@Autowired
	@Qualifier("hiService")
	private HelloService hiService;

	@Autowired
	@Qualifier("welcomeService")
	private HelloService welcomeServiceImpl;

	@GetMapping(value = "/hi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HelloResponse> sayHi() {
		HelloResponse resp = new HelloResponse(hiService.sayHello());
		return ResponseEntity.ok(resp);

	}

	@GetMapping(value = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HelloResponse> sayWelcome() {
		HelloResponse resp = new HelloResponse(welcomeServiceImpl.sayHello());
		return ResponseEntity.ok(resp);

	}

	private static class HelloResponse {
		String resp;

		public HelloResponse() {
		}

		public HelloResponse(String resp) {
			super();
			this.resp = resp;
		}

		public String getResp() {
			return resp;
		}

		public void setResp(String resp) {
			this.resp = resp;
		}
		
		

	}
}
