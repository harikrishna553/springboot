package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint1 {

	@ServiceActivator(inputChannel = "stringChannel")
	public void consumeStringMessage(Message<String> message) {
		System.out.println("ConsumerEndpoint1 -> Received message from stringChannel : " + message.getPayload());
	}

	@ServiceActivator(inputChannel = "integerChannel")
	public void consumeIntegerMessage(Message<String> message) {
		System.out.println("ConsumerEndpoint1 -> Received message from integerChannel : " + message.getPayload());
	}

}
