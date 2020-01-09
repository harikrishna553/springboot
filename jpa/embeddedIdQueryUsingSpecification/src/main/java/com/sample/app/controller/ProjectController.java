package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.Project;
import com.sample.app.model.SearchQuery;
import com.sample.app.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ApiOperation(value = "Projects Filter", notes = "Get projects by search criteria")
	@PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Project>> getEmployees(@RequestBody SearchQuery searchQuery) {
		List<Project> emps = projectService.findAll(searchQuery);

		return new ResponseEntity<>(emps, HttpStatus.OK);
	}
}
