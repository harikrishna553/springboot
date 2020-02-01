package com.sample.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.ProjectDto;
import com.sample.app.entity.Project;
import com.sample.app.repository.ProjectRepository;
import com.sample.app.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository pjtRepo;

	@Override
	public Project getOne(int pjtId) {

		return pjtRepo.getOne(pjtId);

	}

	@Override
	public Project saveProject(ProjectDto pjtDto) {
		Project pjt = new Project();
		pjt.setProjectName(pjtDto.getProjectName());
		pjt.setTeamSize(pjtDto.getTeamSize());
		return pjtRepo.save(pjt);
	}

	@Override
	public List<Project> all() {
		return pjtRepo.findAll();
	}

}
