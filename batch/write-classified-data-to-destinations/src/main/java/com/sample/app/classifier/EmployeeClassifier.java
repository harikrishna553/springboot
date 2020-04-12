package com.sample.app.classifier;

import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import com.sample.app.entity.Employee;

public class EmployeeClassifier implements Classifier<Employee, ItemWriter<? super Employee>> {

	private ItemWriter<Employee> evenItemWriter;
	private ItemWriter<Employee> oddItemWriter;

	public EmployeeClassifier(ItemWriter<Employee> evenItemWriter, ItemWriter<Employee> oddItemWriter) {
		this.evenItemWriter = evenItemWriter;
		this.oddItemWriter = oddItemWriter;
	}

	@Override
	public ItemWriter<? super Employee> classify(Employee employee) {

		int empId = employee.getId();

		if (empId % 2 == 0) {
			return evenItemWriter;
		}

		return oddItemWriter;

	}

}
