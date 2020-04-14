package com.sample.app.itemprocessor;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.sample.app.entity.Employee;

@Component
public class EmployeeNameFilterItemProcessor implements ItemProcessor<Employee, Employee> {

	private static final List<String> NAMES_TO_EXCLUDE = Arrays.asList("Rama", "Sailu");

	@Override
	public Employee process(Employee emp) throws Exception {

		if (NAMES_TO_EXCLUDE.contains(emp.getFirstName()) || NAMES_TO_EXCLUDE.contains(emp.getLastName())) {
			return null;
		}

		return emp;
	}

}
