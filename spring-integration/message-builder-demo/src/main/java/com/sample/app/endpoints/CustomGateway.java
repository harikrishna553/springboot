package com.sample.app.endpoints;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "inputChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "echoChannel", replyTimeout = 2, requestTimeout = 200)
	public void print(Message<String> message);


}
