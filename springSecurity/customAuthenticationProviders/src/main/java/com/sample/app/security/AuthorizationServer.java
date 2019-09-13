package com.sample.app.security;

import java.util.Arrays;
import java.util.List;

public class AuthorizationServer {

	private static final List<String> bearerTokens = Arrays.asList("bearer123", "bearer786");
	private static final List<String> secretTokens = Arrays.asList("secret123", "secret786");

	public static boolean isBearerTokenValid(String token) {
		if (token == null)
			return false;
		return bearerTokens.contains(token);
	}

	public static boolean isSecretTokenValid(String token) {
		if (token == null)
			return false;
		return secretTokens.contains(token);
	}
}
