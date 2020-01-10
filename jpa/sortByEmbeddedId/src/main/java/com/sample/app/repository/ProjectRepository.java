package com.sample.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.entity.Project;
import com.sample.app.entity.ProjectId;

public interface ProjectRepository extends PagingAndSortingRepository<Project, ProjectId>{

}
