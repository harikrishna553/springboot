package com.sample.app.endpoints;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint2 {

	@ServiceActivator(inputChannel = "myQueueChannel", poller = {
			@Poller(maxMessagesPerPoll = "2", fixedRate = "2000") })
	public Message<String> consumeMessage(Message<String> message) {
		System.out.println("ConsumerEndpoint2 -> Received from gateway : " + message.getPayload());
		return MessageBuilder.withPayload("Message '" + message.getPayload() + "' received by ConsumerEndpoint2").build();
	}

}
