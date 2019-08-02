package com.sample.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService empService;

	@InjectMocks
	private EmployeeController employeeController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newEmployee() throws Exception {
		Employee emp1 = new Employee();
		emp1.setFirstName("Anil");
		emp1.setLastName("Kumar");
		emp1.setId(1);

		when(empService.save(any(Employee.class))).thenReturn(emp1);

		String payload = "{\"firstName\" : \"Anil\", \"lastName\" : \"Kumnar\", \"id\" : 1}";

		MvcResult mvcResult = mockMvc
				.perform(post("/api/v1/employees").content(payload).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
		System.out.println(mvcResult.getResponse().getContentAsString());
		// assertEquals("{\"id\":1,\"firstName\":\"Anil\",\"lastName\":\"Kumar\"}",
		// mvcResult.getResponse().getContentAsString());

	}

	@Configuration
	@ComponentScan(basePackageClasses = { EmployeeController.class })
	public static class TestConfiguration {
	}
}
