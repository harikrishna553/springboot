package com.sample.app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.dto.SalaryAccountDto;
import com.sample.app.entity.Employee;
import com.sample.app.entity.SalaryAccount;
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

		SalaryAccountDto salaryAccDto = empDto.getSalaryAccDto();

		SalaryAccount salaryAccount = new SalaryAccount();

		salaryAccount.setAccountNumber(salaryAccDto.getAccountNumber());
		salaryAccount.setAddress(salaryAccDto.getAddress());
		salaryAccount.setBranch(salaryAccDto.getBranch());
		salaryAccount.setIfscCode(salaryAccDto.getIfscCode());

		emp.setSalaryAccount(salaryAccount);

		return empRepo.save(emp);
	}

	@Override
	public Employee getById(int id) {
		return empRepo.findById(id).get();
	}

}
