package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "echoChannel")
	public void print(String message) {
		System.out.println(message);
	}
	
	@ServiceActivator(inputChannel = "reverseEchoChannel")
	public void reversePrint(String message) {
		System.out.println(new StringBuilder(message).reverse().toString());
	}
}
