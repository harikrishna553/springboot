package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.ProjectDto;
import com.sample.app.entity.Project;
import com.sample.app.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService pjtService;

	@ApiOperation(value = "Save Project", notes = "Create new Project")
	@PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> saveProject(@RequestBody ProjectDto pjtDto) {

		Project persistedPjt = pjtService.saveProject(pjtDto);

		return new ResponseEntity<>(persistedPjt, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Projects", notes = "Get All Projects")
	@GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Project>> getProjects() {

		return new ResponseEntity<>(pjtService.all(), HttpStatus.CREATED);
	}

}
