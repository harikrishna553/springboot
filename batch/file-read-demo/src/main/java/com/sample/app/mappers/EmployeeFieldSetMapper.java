package com.sample.app.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.sample.app.model.Employee;

public class EmployeeFieldSetMapper implements FieldSetMapper<Employee> {

	@Override
	public Employee mapFieldSet(FieldSet fieldSet) throws BindException {

		Employee emp = new Employee();

		emp.setId(fieldSet.readInt("id"));
		emp.setFirstName(fieldSet.readString("firstName"));
		emp.setLastName(fieldSet.readString("lastName"));

		return emp;
	}

}
