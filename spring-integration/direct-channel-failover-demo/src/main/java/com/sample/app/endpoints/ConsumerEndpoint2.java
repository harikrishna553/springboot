package com.sample.app.endpoints;

import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint2 {

	@ServiceActivator(inputChannel = "directChannel")
	@Order(1)
	public Message<String> consumeMessage(Message<String> message) {
		throw new RuntimeException("Consumer 2 is down");
	}

}
