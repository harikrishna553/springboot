package com.sample.app.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

	Page<Employee> findByFirstName(String firstName, Pageable pageable);
	
	Page<Employee> findByAge(int age, Pageable pageable);
}

