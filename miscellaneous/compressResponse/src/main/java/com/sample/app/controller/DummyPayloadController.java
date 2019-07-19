package com.sample.app.controller;

import java.security.SecureRandom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyPayloadController {
	private static final String ALPHA_NUMERICALS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();
	private static final int LENGTH = ALPHA_NUMERICALS.length();

	@RequestMapping("/randomData")
	public String dummyData() {
		return randomString(10000);
	}

	public static String randomString(int len) {
		final StringBuilder builder = new StringBuilder(len);

		for (int i = 0; i < len; i++)
			builder.append(ALPHA_NUMERICALS.charAt(rnd.nextInt(LENGTH)));

		return builder.toString();
	}

}
