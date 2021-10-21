package com.sample.app.service;

import java.util.List;

import com.sample.app.dto.ProjectRequestDto;
import com.sample.app.dto.ProjectResponseDto;
import com.sample.app.exceptions.AppException;

public interface ProjectService {
	
	ProjectResponseDto createProject(ProjectRequestDto projectCreateRequestDto);
	
	ProjectResponseDto updateProject(Integer projectId, ProjectRequestDto projectCreateRequestDto) throws AppException;
	
	List<ProjectResponseDto> all();

}
