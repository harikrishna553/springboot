package com.sample.app.service;

import java.util.Map;
import java.util.Set;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "printChannel")
	public String consumeStringMessage(Message<String> message) {
		System.out.println("Received message from printChannel : " + message.getPayload());
		
		Set<Map.Entry<String, Object>> messageHeaders = message.getHeaders().entrySet();
		
		System.out.println("Headers are.....");
		for(Map.Entry<String, Object> entry: messageHeaders) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		
		return "Success";
	}
	

}
