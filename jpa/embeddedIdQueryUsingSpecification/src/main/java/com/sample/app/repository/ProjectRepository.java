package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.entity.Project;
import com.sample.app.entity.ProjectId;

public interface ProjectRepository extends JpaSpecificationExecutor<Project>, PagingAndSortingRepository<Project, ProjectId>{

}