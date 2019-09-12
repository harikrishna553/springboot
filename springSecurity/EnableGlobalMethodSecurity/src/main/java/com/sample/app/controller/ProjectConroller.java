package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Project;
import com.sample.app.repository.ProjectRepository;

@RestController
public class ProjectConroller {
	
	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping("/rest/project")
	public Project getProject(@RequestParam("pjtName") String projectName) {
		return projectRepository.findByProjectName(projectName);
	}
	
	@RequestMapping("/")
	public String sayHello() {
		return "Hello World";
	}
}
