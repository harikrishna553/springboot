package com.sample.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.app.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeControllerIntegrationTest {

	@Autowired
	private EmployeeController empController;

	@Test
	public void newEmployee() {
		Employee emp1 = new Employee();
		emp1.setFirstName("Anil");
		emp1.setLastName("Kumar");

		ResponseEntity<Employee> response = empController.save(emp1);

		Employee persistedEmployee = response.getBody();

		assertNotNull(persistedEmployee);
		assertNotNull(persistedEmployee.getId());
		assertEquals(emp1.getFirstName(), persistedEmployee.getFirstName());
		assertEquals(emp1.getLastName(), persistedEmployee.getLastName());
	}

}
