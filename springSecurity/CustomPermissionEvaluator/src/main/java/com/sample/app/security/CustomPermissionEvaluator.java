package com.sample.app.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if ((authentication == null) || !(permission instanceof String)) {
			return false;
		}
		
		return hasPermission(authentication, permission);
		
	}
	
	public boolean hasPermission(Authentication authentication, Object permission) {
		for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
			SimpleGrantedAuthority authority = (SimpleGrantedAuthority) grantedAuth;

			if (authority.getAuthority().toUpperCase().equals(permission.toString().toUpperCase())) {
				return true;
			}

		}

		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {

		if ((authentication == null) || !(permission instanceof String)) {
			return false;
		}

		return hasPermission(authentication, permission);
	}

}
