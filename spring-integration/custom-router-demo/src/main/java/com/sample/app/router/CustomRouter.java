package com.sample.app.router;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class CustomRouter extends AbstractMessageRouter {

	@Autowired
	@Qualifier("secretChannel")
	private MessageChannel secretChannel;

	@Autowired
	@Qualifier("publicChannel")
	private MessageChannel publicChannel;

	@Autowired
	@Qualifier("defaultOutputChannel")
	private MessageChannel defaultOutputChannel;

	@Override
	protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {

		Collection<MessageChannel> result = new ArrayList<>();

		String payload = message.getPayload().toString();

		if (payload.contains("secret")) {
			result.add(secretChannel);
		} else if (payload.contains("public")) {
			result.add(publicChannel);
		} else {
			result.add(defaultOutputChannel);
		}

		// TODO Auto-generated method stub
		return result;
	}

}
