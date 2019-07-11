package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
