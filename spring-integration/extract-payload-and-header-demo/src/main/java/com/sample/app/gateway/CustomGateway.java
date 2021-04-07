package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface CustomGateway {

	@Gateway(requestChannel = "printChannel")
	public void print(Message<String> message);

}
