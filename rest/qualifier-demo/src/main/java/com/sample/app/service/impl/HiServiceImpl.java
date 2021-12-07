package com.sample.app.service.impl;

import org.springframework.stereotype.Component;

import com.sample.app.service.HelloService;

@Component("hiService")
public class HiServiceImpl implements HelloService{

	@Override
	public String sayHello() {
		return "Hi!!!!!!!!";
	}

}
