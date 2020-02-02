package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.dto.ProjectDto;
import com.sample.app.entity.Employee;
import com.sample.app.entity.Project;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	@Transactional
	public Employee save(EmployeeDto empDto) {

		String firstName = empDto.getFirstName();

		String lastName = empDto.getLastName();

		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);

		List<Project> projectToBeSaved = new ArrayList<>();

		for (ProjectDto dto : empDto.getProjects()) {
			Project tempPjt = new Project();

			tempPjt.setProjectName(dto.getProjectName());
			tempPjt.setTeamSize(dto.getTeamSize());

			projectToBeSaved.add(tempPjt);

		}

		emp.setProjects(new HashSet<>(projectToBeSaved));

		return empRepo.save(emp);
	}

	@Override
	public Employee getById(int id) {
		return empRepo.findById(id).get();
	}

}
