package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "headerValueRouterChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "headerValueRouterChannel")
	public void print(Message<?> message);

}
