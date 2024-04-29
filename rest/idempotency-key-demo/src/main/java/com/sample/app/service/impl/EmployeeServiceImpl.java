package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeRequestPayload;
import com.sample.app.model.EmployeesResponse;
import com.sample.app.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final String IDEMPOTENCY_KEY = "Idempotency-Key";
	private static final Map<String, Employee> EMPLOYEE_MAP = new ConcurrentHashMap<>();

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Override
	public Employee createEmployee(EmployeeRequestPayload empToCreate) {

		String idempotencyKey = httpServletRequest.getHeader(IDEMPOTENCY_KEY);
		if (idempotencyKey != null) {
			synchronized (EMPLOYEE_MAP) {
				Employee emp = EMPLOYEE_MAP.get(idempotencyKey);
				if (emp != null) {
					return emp;
				}

				emp = Employee.newEmployee(empToCreate);
				EMPLOYEE_MAP.put(idempotencyKey, emp);
				return emp;
			}
		}

		throw new IllegalArgumentException(IDEMPOTENCY_KEY + " header is missing");
	}

	@Override
	public EmployeesResponse emps() {
		List<Employee> emps = new ArrayList<>(EMPLOYEE_MAP.values());
		EmployeesResponse empResponse = new EmployeesResponse();
		empResponse.setEmps(emps);
		return empResponse;
	}

}