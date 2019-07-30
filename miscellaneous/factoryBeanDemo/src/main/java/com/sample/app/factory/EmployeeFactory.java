package com.sample.app.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import com.sample.app.model.Employee;

@Component
public class EmployeeFactory implements FactoryBean<Employee> {

	@Override
	public Employee getObject() throws Exception {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Krishna");
		emp.setLastName("Majety");
		return emp;
	}

	@Override
	public Class<?> getObjectType() {
		return Employee.class;
	}

}
