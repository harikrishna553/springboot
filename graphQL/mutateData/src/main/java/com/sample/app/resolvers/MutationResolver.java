package com.sample.app.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sample.app.entity.Address;
import com.sample.app.entity.Department;
import com.sample.app.entity.Employee;
import com.sample.app.entity.EmployeeInput;
import com.sample.app.repository.EmployeeRepository;

@Component
public class MutationResolver implements GraphQLMutationResolver {
	@Autowired
	private EmployeeRepository empRepo;

	public Employee createEmployee(EmployeeInput empInput) {

		Address tempAddress = new Address(empInput.getTemporaryAddress().getCity(),
				empInput.getTemporaryAddress().getState(), empInput.getTemporaryAddress().getCountry());
		Address permAddress = new Address(empInput.getPermanentAddress().getCity(),
				empInput.getPermanentAddress().getState(), empInput.getPermanentAddress().getCountry());
		Department department = new Department(empInput.getDepartment().getDepartmentName());

		Employee empToSave = Employee.builder().firstName(empInput.getFirstName()).lastName(empInput.getLastName())
				.email(empInput.getEmail()).temporaryAddress(tempAddress).permanentAddress(permAddress)
				.department(department).build();

		return empRepo.save(empToSave);
	}
}
