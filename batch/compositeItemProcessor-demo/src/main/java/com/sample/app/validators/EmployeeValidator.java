package com.sample.app.validators;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import com.sample.app.entity.Employee;

public class EmployeeValidator implements Validator<Employee> {

	@Override
	public void validate(Employee emp) throws ValidationException {

		if (emp.getFirstName() == null) {
			throw new ValidationException("firstName must not be null");
		}

		if (emp.getLastName() == null) {
			throw new ValidationException("lastName must not be null");
		}

	}

}
