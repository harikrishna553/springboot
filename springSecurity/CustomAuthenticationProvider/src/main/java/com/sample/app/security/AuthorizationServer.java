package com.sample.app.security;

import java.util.Arrays;
import java.util.List;

public class AuthorizationServer {

	private static final List<String> tokens = Arrays.asList("token123", "token786");

	public static boolean isTokenValid(String token) {
		if (token == null)
			return false;
		return tokens.contains(token);
	}
}
