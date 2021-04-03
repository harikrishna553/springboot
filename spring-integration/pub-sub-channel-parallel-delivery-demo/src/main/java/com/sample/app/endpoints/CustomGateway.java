package com.sample.app.endpoints;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "pubSubChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "pubSubChannel")
	public void print(Message<String> message);

}
