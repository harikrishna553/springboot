package com.sample.app.service;

import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeRequestPayload;
import com.sample.app.model.EmployeesResponse;

public interface EmployeeService {
	
	Employee createEmployee(EmployeeRequestPayload empToCreate);
	
	EmployeesResponse emps();

}
