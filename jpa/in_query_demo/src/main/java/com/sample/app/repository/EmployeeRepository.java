package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findByIdIn(List<Integer> ids);
	
	public List<Employee> findByIdIsIn(List<Integer> ids);
	
	@Query( "select e from Employee e where id in :ids" )
	public List<Employee> getEmpsByIds(@Param("ids") List<Integer> ids);
	
	@Query(value="select * from employees e where e.employee_id in :ids", nativeQuery=true)
	public List<Employee> getEmpsByIdsUsingNativeQuery(@Param("ids") List<Integer> ids);
}
