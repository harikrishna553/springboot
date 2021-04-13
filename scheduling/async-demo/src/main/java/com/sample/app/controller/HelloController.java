package com.sample.app.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.service.SecretService;

@RestController
public class HelloController {

	@Autowired
	private SecretService secretService;

	@RequestMapping("/secret-message")
	public String home() throws InterruptedException, ExecutionException {
		return secretService.getSecretMessage();
	}
}