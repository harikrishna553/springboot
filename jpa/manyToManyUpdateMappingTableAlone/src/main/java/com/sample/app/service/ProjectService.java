package com.sample.app.service;

import java.util.List;

import com.sample.app.dto.ProjectDto;
import com.sample.app.entity.Project;

public interface ProjectService {

	Project getOne(int pjtId);
	
	Project saveProject(ProjectDto pjtDto);
	
	List<Project> all();
	
}
