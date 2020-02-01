package com.sample.app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.entity.Employee;
import com.sample.app.entity.Project;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.repository.ProjectRepository;
import com.sample.app.service.EmployeeService;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ProjectRepository pjtRepo;

	@Override
	@Transactional
	public Employee save(EmployeeDto empDto) {

		String firstName = empDto.getFirstName();

		String lastName = empDto.getLastName();

		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);

		List<Project> pjtsProxies = new ArrayList<>();

		for (int pjtId : empDto.getProjectIds()) {
			Project tempPjt = pjtRepo.getOne(pjtId);
			pjtsProxies.add(tempPjt);
		}

		emp.setProjects(new HashSet<>(pjtsProxies));

		return empRepo.save(emp);
	}

	@Override
	public Employee getById(int id) {
		return empRepo.findById(id).get();
	}

	@Override
	public Employee update(int id, EmployeeDto empDto) {
		String firstName = empDto.getFirstName();

		String lastName = empDto.getLastName();

		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setId(id);

		List<Project> pjtsProxies = new ArrayList<>();

		for (int pjtId : empDto.getProjectIds()) {
			Project tempPjt = pjtRepo.getOne(pjtId);
			pjtsProxies.add(tempPjt);
		}

		emp.setProjects(new HashSet<> (pjtsProxies));

		return empRepo.save(emp);
	}

}
