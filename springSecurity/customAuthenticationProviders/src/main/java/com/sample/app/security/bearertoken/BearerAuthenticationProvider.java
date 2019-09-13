package com.sample.app.security.bearertoken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sample.app.exception.AuthException;
import com.sample.app.security.AuthorizationServer;

@Component
public class BearerAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	public boolean supports(Class<?> authentication) {
		return (BearerAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		BearerAuthenticationToken token = (BearerAuthenticationToken) authentication;

		if (!AuthorizationServer.isBearerTokenValid(token.getToken())) {
			throw new AuthException("Token is not valid");
		}

		return new BearerUserDetails(token.getToken());

	}

}
