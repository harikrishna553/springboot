package com.sample.app.endpoints;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(name = "myGateway", defaultRequestChannel = "inputChannel")
public interface ProducerEndpoint {

	@Gateway(requestChannel = "inputChannel", replyTimeout = 2, requestTimeout = 200)
	public void produceMessage(String message);
	

}
