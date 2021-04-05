package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

	@ServiceActivator(inputChannel = "outputChannel")
	public void consumeStringMessage(Message<String> message) {
		System.out.println("Received message from outputChannel : " + message.getPayload());
	}
}
