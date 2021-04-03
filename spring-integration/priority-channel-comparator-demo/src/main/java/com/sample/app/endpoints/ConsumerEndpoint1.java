package com.sample.app.endpoints;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint1 {

	@ServiceActivator(inputChannel = "myQueueChannel", poller = {
			@Poller(maxMessagesPerPoll = "1", fixedDelay = "2000") })
	public Message<String> consumeMessage(Message<Integer> message) {
		System.out.println("ConsumerEndpoint1 -> Received from gateway : " + message.getPayload());
		return MessageBuilder.withPayload("Message '" + message.getPayload() + "' received by ConsumerEndpoint1")
				.build();
	}

}
