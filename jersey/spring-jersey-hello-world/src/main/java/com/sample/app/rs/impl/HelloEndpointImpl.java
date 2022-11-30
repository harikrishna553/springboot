package com.sample.app.rs.impl;

import org.springframework.stereotype.Service;

import com.sample.app.rs.HelloEndpoint;

@Service
public class HelloEndpointImpl implements HelloEndpoint{

	public String sayHello() {
		return "Hello World";
	}
}
