package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint3 {

	@ServiceActivator(inputChannel = "pubSubChannel")
	public Message<String> consumeMessage(Message<String> message) {
		System.out.println("ConsumerEndpoint3 -> Received message from gateway : " + message.getPayload());
		return MessageBuilder.withPayload("Message '" + message.getPayload() + "' received by ConsumerEndpoint3")
				.build();
	}

}
