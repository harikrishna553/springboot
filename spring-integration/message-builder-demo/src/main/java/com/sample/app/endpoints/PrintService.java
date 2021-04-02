package com.sample.app.endpoints;

import java.util.Map;
import java.util.Set;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

	@ServiceActivator(inputChannel = "echoChannel")
	public void print(Message<String> message) {
		String payload = message.getPayload();
		MessageHeaders messageHeaders = message.getHeaders();

		System.out.println("Headers");
		Set<Map.Entry<String, Object>> messageHeadersSet = messageHeaders.entrySet();

		for (Map.Entry<String, Object> headerEntry : messageHeadersSet) {
			System.out.println(headerEntry.getKey() + " -> " + headerEntry.getValue());
		}

		System.out.println("\nPayload");
		System.out.println(payload);
	}

}
