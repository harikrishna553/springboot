package com.sample.app.repository.interfaces;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.sample.app.model.Role;

public interface UserBasicInfoOpenProjection {
	public Integer getId();

	@Value("#{target.firstName} #{target.lastName}")
	public String getFullName();

	public Set<Role> getRoles();
}
