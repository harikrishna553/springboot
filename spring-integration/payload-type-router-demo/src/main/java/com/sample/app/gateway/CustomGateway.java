package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "payloadTypeRoutingChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "payloadTypeRoutingChannel")
	public void print(Message<?> message);

}
