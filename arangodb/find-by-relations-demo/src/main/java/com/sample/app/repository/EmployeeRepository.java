package com.sample.app.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.Employee;

public interface EmployeeRepository extends ArangoRepository<Employee, String> {

	public List<Employee> findByProjectsProjectName(String projectName);

}