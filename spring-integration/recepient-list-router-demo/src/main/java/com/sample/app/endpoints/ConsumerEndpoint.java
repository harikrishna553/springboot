package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

	@ServiceActivator(inputChannel = "secretChannel")
	public void consumeStringMessage(Message<String> message) {
		System.out.println("Received message from secretChannel : " + message.getPayload());
	}

	@ServiceActivator(inputChannel = "publicChannel")
	public void consumeIntegerMessage(Message<String> message) {
		System.out.println("Received message from publicChannel : " + message.getPayload());
	}

	@ServiceActivator(inputChannel = "defaultOutputChannel")
	public void defaultOutputChannelMsg(Message<String> message) {
		System.out.println("Received message from defaultOutputChannel : " + message.getPayload());
	}
}
