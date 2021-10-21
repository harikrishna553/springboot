package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sample.app.constants.AppConstants;
import com.sample.app.dto.ProjectRequestDto;
import com.sample.app.dto.ProjectResponseDto;
import com.sample.app.entity.Project;
import com.sample.app.entity.Role;
import com.sample.app.exceptions.AppException;
import com.sample.app.repository.ProjectRepository;
import com.sample.app.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public ProjectResponseDto createProject(ProjectRequestDto projectCreateRequestDto) {

		Project project = new Project();
		project.setName(projectCreateRequestDto.getName());
		project.setDescription(projectCreateRequestDto.getDescription());

		List<Role> roles = new ArrayList<>();
		if (projectCreateRequestDto.getRoles() != null) {

			for (String role : projectCreateRequestDto.getRoles()) {
				Role roleToCreate = new Role();
				roleToCreate.setRoleName(role);
				roleToCreate.setProject(project);
				roleToCreate.setActive(AppConstants.ACTIVE);
				roles.add(roleToCreate);
			}
		}

		project.setRoles(new HashSet(roles));

		project = projectRepository.save(project);
		return convert(project);

	}

	@Override
	public ProjectResponseDto updateProject(Integer projectId, ProjectRequestDto projectCreateRequestDto)
			throws AppException {

		Optional<Project> projectOpt = projectRepository.findById(projectId);

		if (!projectOpt.isPresent()) {
			throw new AppException("Project not found", HttpStatus.NOT_FOUND);
		}

		Project project = projectOpt.get();
		Set<Role> roles = project.getRoles();

		Map<String, Role> rolesMap = new HashMap<>();

		for (Role role : roles) {
			role.setActive(AppConstants.INACTIVE);
			rolesMap.put(role.getRoleName(), role);
		}

		Set<String> existingRoleNames = rolesMap.keySet();

		List<String> roleNames = projectCreateRequestDto.getRoles();
		for (String newRoleName : roleNames) {
			if (!existingRoleNames.contains(newRoleName)) {
				Role newRoleToCreate = new Role();
				newRoleToCreate.setProject(project);
				newRoleToCreate.setActive(AppConstants.ACTIVE);
				newRoleToCreate.setRoleName(newRoleName);
				roles.add(newRoleToCreate);
			} else {
				Role persistedRole = rolesMap.get(newRoleName);
				persistedRole.setActive(AppConstants.ACTIVE);
			}
		}

		project = projectRepository.save(project);
		return convert(project);
	}

	@Override
	public List<ProjectResponseDto> all() {
		List<Project> projects = projectRepository.findAll();
		return convert(projects);
	}

	private ProjectResponseDto convert(Project project) {
		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		projectResponseDto.setId(project.getId());
		projectResponseDto.setName(project.getName());
		projectResponseDto.setDescription(project.getDescription());
		projectResponseDto.setRoles(project.getRoles());

		return projectResponseDto;
	}

	private List<ProjectResponseDto> convert(List<Project> projects) {
		List<ProjectResponseDto> finalList = new ArrayList<>();

		for (Project project : projects) {
			finalList.add(convert(project));
		}

		return finalList;
	}

}