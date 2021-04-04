package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "pollableChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "pollableChannel")
	public void print(Message<String> message);

}
