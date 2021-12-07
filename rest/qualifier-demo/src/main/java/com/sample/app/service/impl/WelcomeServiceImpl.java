package com.sample.app.service.impl;

import org.springframework.stereotype.Component;

import com.sample.app.service.HelloService;

@Component("welcomeService")
public class WelcomeServiceImpl implements HelloService{

	@Override
	public String sayHello() {
		return "Welcome!!!!!!";
	}

}
