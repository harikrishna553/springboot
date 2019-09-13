package com.sample.app.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BearerAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String token;

	public BearerAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	public String getToken() {
		return token;
	}

}
