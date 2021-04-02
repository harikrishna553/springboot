package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

	@ServiceActivator(inputChannel = "myChannel")
	public Message<String> consumeMessage(Message<String> message) {
		System.out.println("Received from Producer : " + message);
		return MessageBuilder.withPayload("Message Received").build();
	}

}
