package com.sample.app.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "printChannel")
	public String consumeStringMessage(Message<String> message) throws InterruptedException {
		
		Random random = new Random();
		int timeToSleep = random.nextInt(10);
		
		TimeUnit.SECONDS.sleep(timeToSleep);
		
		System.out.println("Received message from printChannel : " + message.getPayload());		
		return "Message " + message.getPayload() + " received";
	}
	

}
