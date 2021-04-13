package com.sample.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.entity.Project;
import com.sample.app.entity.ProjectId;

public interface ProjectRepository extends PagingAndSortingRepository<Project, ProjectId> {

	List<Project> findByProjectId_ProjectName(String projectName);
	
	List<Project> findByProjectId_Organization(String organization);
	
	List<Project> findByProjectId_ProjectNameAndProjectId_Organization(String projectName, String organization);
}