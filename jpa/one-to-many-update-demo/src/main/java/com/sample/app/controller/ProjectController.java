
package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.ProjectRequestDto;
import com.sample.app.dto.ProjectResponseDto;
import com.sample.app.exceptions.AppException;
import com.sample.app.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/v1/projects")
@Api(tags = "Projects", description = "This section contains APIs for Projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ApiOperation(value = "Get all Projects")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectResponseDto>> getProjects() {
		List<ProjectResponseDto> projects = projectService.all();

		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@ApiOperation(value = "Create Project")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectResponseDto> createProject(
			@ApiParam(name = "ProjectDetails", value = "Project Details Object", required = true) @RequestBody ProjectRequestDto projectRequestDto) {
		ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto);

		return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update Project")
	@PutMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectResponseDto> updateProject(
			@ApiParam(name = "projectId", value = "Numerical denoting "
					+ "pro id, for ex. 1.", required = true) @PathVariable(name = "projectId") Integer projectId,
			@ApiParam(name = "ProjectDetails", value = "Project Details Object", required = true) @RequestBody ProjectRequestDto projectRequestDto) throws AppException {
		ProjectResponseDto projectResponseDto = projectService.updateProject(projectId, projectRequestDto);

		return new ResponseEntity<>(projectResponseDto, HttpStatus.CREATED);
	}
}
