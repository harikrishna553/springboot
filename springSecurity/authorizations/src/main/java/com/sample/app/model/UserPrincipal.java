package com.sample.app.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<UserAuthorizations> authGroups;

	public UserPrincipal(final User user, final List<UserAuthorizations> authGroups) {
		super();
		this.user = user;
		this.authGroups = authGroups;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authGroups == null) return Collections.EMPTY_SET;
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<> ();
		
		for(UserAuthorizations authGroup: authGroups) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
		}
		
		return grantedAuthorities;
		
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
