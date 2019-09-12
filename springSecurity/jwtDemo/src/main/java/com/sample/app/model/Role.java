package com.sample.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ADMIN, ROLE_USER, ROLE_SUPPORT;

	@Override
	public String getAuthority() {
		return name();
	}

}
