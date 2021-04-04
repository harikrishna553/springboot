package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway")
public interface CustomGateway {

	@Gateway(requestChannel = "customRouterChannel")
	public void print(Message<?> message);

}
