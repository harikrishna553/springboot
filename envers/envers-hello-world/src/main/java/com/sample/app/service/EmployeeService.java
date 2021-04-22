package com.sample.app.service;

import com.sample.app.dto.EmployeeRequestDto;
import com.sample.app.entity.Employee;

public interface EmployeeService {
	
	public Employee save(EmployeeRequestDto emp);
	
	public Employee byId(Integer id);
	
	public Employee update(Integer id, EmployeeRequestDto empDto);

}
