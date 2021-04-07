package com.sample.app.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "printChannel")
	public void consumeStringMessage(Message<String> message) throws InterruptedException {
		System.out.println("Received message from printChannel : " + message.getPayload());		
	}
	

}
