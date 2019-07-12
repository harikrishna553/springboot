package com.sample.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController empController;

	@Mock
	private EmployeeService empService;

	@Before
	public void init() {
		/* This statement look for @InjectMocks and @Mock fields and init the mocks */
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testById() {

		Employee mockedEmp = new Employee();
		mockedEmp.setId(1);

		Optional<Employee> empToReturn = Optional.of(mockedEmp);

		when(empService.getEmployee(1)).thenReturn(empToReturn);

		Employee employee = empController.byId(1).getBody();

		// Tests that getEmployee method called once.
		verify(empService).getEmployee(1);

		assertEquals(1, employee.getId());

	}
}
