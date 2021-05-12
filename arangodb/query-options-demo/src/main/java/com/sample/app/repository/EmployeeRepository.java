package com.sample.app.repository;

import java.util.Map;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.BindVars;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.annotation.QueryOptions;
import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.Employee;

public interface EmployeeRepository extends ArangoRepository<Employee, String> {

	@Query("FOR e IN employees FILTER e.lastName == @lName SORT e.age ASC RETURN e ")
	@QueryOptions(count = true)
	public ArangoCursor<Employee> getWithLastName(@BindVars Map<String, Object> bindvars);

}