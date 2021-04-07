package com.sample.app.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "secretMsgChannel", outputChannel = "notifySecretMsg")
	public String consumeStringMessage(Message<String> message) throws InterruptedException {
		System.out.println("Received message from secretMsgChannel : " + message.getPayload());

		return message.getPayload();
	}

	@ServiceActivator(inputChannel = "notifySecretMsg")
	public void notifyExternalService(String msg) {
		System.out.println("Acknowledgement received for the message '" + msg + "'");
	}
}
