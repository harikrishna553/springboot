package com.sample.app.endpoints;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "directChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "directChannel")
	public Message<String> print(Message<String> message);

}
