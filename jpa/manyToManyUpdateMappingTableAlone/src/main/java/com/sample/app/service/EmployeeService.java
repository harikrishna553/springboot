package com.sample.app.service;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.entity.Employee;

public interface EmployeeService {

		Employee save(EmployeeDto emp);
		
		Employee getById(int id);

		Employee update(int id, EmployeeDto empDto);
		
}
