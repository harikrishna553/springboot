package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeCustomRepository{

}
