package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

	@ServiceActivator(inputChannel = "myOutputChannel")
	public void consumeStringMessage(String message) {
		System.out.println("Received message from myOutputChannel : " + message);
	}

	@ServiceActivator(inputChannel = "myInputChannel")
	public String toUppercase(Message<String> message) {
		System.out.println("Received message from myInputChannel : " + message.getPayload());

		return message.getPayload().toUpperCase();
	}
}


