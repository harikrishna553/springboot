package com.sample.app.security.secrettoken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sample.app.exception.AuthException;
import com.sample.app.security.AuthorizationServer;

@Component
public class SecretAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	public boolean supports(Class<?> authentication) {
		return (SecretAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		SecretAuthenticationToken token = (SecretAuthenticationToken) authentication;

		if (token.getToken() == null || !AuthorizationServer.isSecretTokenValid(token.getToken())) {
			throw new AuthException("Token is not valid or not provided");
		}

		return new SecretUserDetails(token.getToken());

	}
}
