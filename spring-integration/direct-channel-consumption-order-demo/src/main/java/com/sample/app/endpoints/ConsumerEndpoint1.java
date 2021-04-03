package com.sample.app.endpoints;

import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint1 {

	@ServiceActivator(inputChannel = "directChannel")
	@Order(3)
	public Message<String> consumeMessage(Message<String> message) {
		System.out.println("ConsumerEndpoint1 -> Received message from gateway : " + message.getPayload());
		return MessageBuilder.withPayload("Message '" + message.getPayload() + "' received by ConsumerEndpoint1")
				.build();
	}

}
