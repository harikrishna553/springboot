package com.sample.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class BearerTokenFilter extends AbstractAuthenticationProcessingFilter {

	BearerTokenFilter(final RequestMatcher requiresAuth) {
		super(requiresAuth);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		BearerAuthenticationToken token = new BearerAuthenticationToken(request.getHeader("token"));

		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);

	}

}
