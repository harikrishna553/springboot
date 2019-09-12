package com.sample.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenFilter extends OncePerRequestFilter {

	private JWTTokenProvider jwtTokenProvider;

	public JWTTokenFilter(JWTTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = jwtTokenProvider.getToken(request);

		if (token == null || token.isEmpty()) {
			SecurityContextHolder.clearContext();
			response.sendError(403, "Bearer Token not found");
			return;
		}

		if (!jwtTokenProvider.isTokenValid(token)) {
			SecurityContextHolder.clearContext();
			response.sendError(403, "Token is not valid");
			return;
		}

		Authentication auth = jwtTokenProvider.getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(auth);

		filterChain.doFilter(request, response);

	}

}
