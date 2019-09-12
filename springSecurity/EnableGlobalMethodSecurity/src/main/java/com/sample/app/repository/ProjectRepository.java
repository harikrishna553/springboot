package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.sample.app.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Project findByProjectName(String name);
}
