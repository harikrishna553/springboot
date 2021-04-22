package com.sample.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.dto.EmployeeRequestDto;
import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Transactional
	@Override
	public Employee save(EmployeeRequestDto empDto) {
		Employee emp = new Employee();
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());

		return empRepository.save(emp);
	}

	@Override
	public Employee byId(Integer id) {
		return empRepository.findById(id).get();
	}

	@Transactional
	@Override
	public Employee update(Integer id, EmployeeRequestDto empDto) {

		Employee emp = empRepository.findById(id).get();

		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());

		return empRepository.save(emp);
	}

}
