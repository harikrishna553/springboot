package com.sample.app.repository.interfaces;

import java.util.Set;

import com.sample.app.model.Role;

public interface UserBasicInfo {
	public Integer getId();

	public String getFirstName();

	public Set<Role> getRoles();

}
