package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface CustomGateway {

	@Gateway(requestChannel = "myUpperCaseChannel")
	public void sendToUppercaseChannel(String message);

}
