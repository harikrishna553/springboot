package com.sample.app.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class CustomChannelInterceptor implements ChannelInterceptor {

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		System.out.println("preSend: Converting the data to uppercase");

		String payloadInUppercase = message.getPayload().toString().toUpperCase();
		Map<String, Object> headers = getHeaders(message);
		Message msg = MessageBuilder.withPayload(payloadInUppercase).copyHeaders(headers).build();

		return ChannelInterceptor.super.preSend(msg, channel);
	}

	private static Map<String, Object> getHeaders(Message source) {
		Set<Map.Entry<String, Object>> headersEntrySet = source.getHeaders().entrySet();
		Map<String, Object> headersMap = new HashMap<>();

		for (Map.Entry<String, Object> entry : headersEntrySet) {
			headersMap.put(entry.getKey(), entry.getValue());
		}
		return headersMap;

	}
}
