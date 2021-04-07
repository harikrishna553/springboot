package com.sample.app.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "printChannel")
	public void consumeStringMessage(@Payload String msg, @Header("msgId") String msgId) {
		System.out.println("Received message from printChannel : " + msg);
		System.out.println("Message id : " + msgId);

	}

}