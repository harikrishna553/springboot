package com.sample.app.repository;

import java.util.List;
import java.util.Map;

import com.arangodb.springframework.annotation.BindVars;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.Employee;

public interface EmployeeRepository extends ArangoRepository<Employee, String> {

	@Query("FOR e IN employees FILTER e.lastName == @lName SORT e.age ASC RETURN e")
	public List<Employee> getWithLastName(@BindVars Map<String, Object> bindvars);

}