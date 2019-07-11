package com.sample.app.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}

