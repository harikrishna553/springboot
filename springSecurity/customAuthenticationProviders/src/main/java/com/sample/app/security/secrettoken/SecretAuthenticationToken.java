package com.sample.app.security.secrettoken;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SecretAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String token;

	public SecretAuthenticationToken(String token) {
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
