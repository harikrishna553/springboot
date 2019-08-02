package com.sample.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.sample.app.dao.impl.EmployeeDaoImpl;
import com.sample.app.model.Employee;
import com.sample.app.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class EmployeeServiceUnitTest {

	@Mock
	private EmployeeDaoImpl empDao;

	@InjectMocks
	private EmployeeServiceImpl empService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newEmployee() {
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setFirstName("Anil");
		emp1.setLastName("Kumar");

		when(empDao.save(any(Employee.class))).thenReturn(emp1);

		Employee persistedEmployee = empService.save(emp1);

		assertNotNull(persistedEmployee);
		assertNotNull(persistedEmployee.getId());
		assertEquals(emp1.getFirstName(), persistedEmployee.getFirstName());
		assertEquals(emp1.getLastName(), persistedEmployee.getLastName());
	}
}
