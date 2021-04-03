package com.sample.app.endpoints;

import java.util.concurrent.Future;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "myQueueChannel")
public interface CustomGateway {

	@Gateway(requestChannel = "myQueueChannel")
	public Future<Message<Integer>> print(Message<Integer> message);

}
