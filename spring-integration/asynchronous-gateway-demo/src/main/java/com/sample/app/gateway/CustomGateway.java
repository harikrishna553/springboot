package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.util.concurrent.ListenableFuture;

@MessagingGateway
public interface CustomGateway {

	@Gateway(requestChannel = "printChannel")
	public ListenableFuture<String> sendToPrintChannel(String message);

}
