package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>, PagingAndSortingRepository<Employee, Integer>{

}
