package com.sample.app.itemprocessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.sample.app.entity.Employee;

@Component
public class EmployeeNameUpperCaseItemProcessor implements ItemProcessor<Employee, Employee> {

  @Override
  public Employee process(Employee emp) throws Exception {

    Employee newEmp = new Employee();

    newEmp.setId(emp.getId());
    newEmp.setFirstName(emp.getFirstName().toUpperCase());
    newEmp.setLastName(emp.getLastName().toUpperCase());

    return newEmp;
  }

}