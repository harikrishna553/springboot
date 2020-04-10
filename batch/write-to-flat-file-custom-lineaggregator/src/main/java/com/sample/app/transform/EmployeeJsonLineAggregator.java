package com.sample.app.transform;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sample.app.entity.Employee;

public class EmployeeJsonLineAggregator implements LineAggregator<Employee> {

	private ObjectMapper objectMapper = new ObjectMapper();

	{
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public String aggregate(Employee emp) {

		try {
			return objectMapper.writeValueAsString(emp);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Occured while serializing Employee instance : " + emp);
		}
	}

}
