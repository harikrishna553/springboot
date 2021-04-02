package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class ProducerAckEndpoint {

	@ServiceActivator(inputChannel = "ackChannel")
	public void receiveAcknowledgement(String message) {
		System.out.println("Received acknowledgement Consumer : " + message);
	}

}
