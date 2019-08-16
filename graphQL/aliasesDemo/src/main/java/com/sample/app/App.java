package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Address;
import com.sample.app.entity.Department;
import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {

	@Autowired
	public EmployeeRepository empRepo;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = Employee.builder().firstName("ram").lastName("Gurram").email("krishna@abc.com")
					.permanentAddress(new Address("Bangalore", "Karnataka", "India"))
					.temporaryAddress(new Address("Hyderabad", "Telangana", "India"))
					.department(new Department("Infrastructure Services")).build();

			Employee emp2 = Employee.builder().firstName("Joel").lastName("Chelli").email("joel@abc.com")
					.permanentAddress(new Address("Chennai", "Tamilnadu", "India"))
					.temporaryAddress(new Address("Hyderabad", "Telangana", "India"))
					.department(new Department("Core Systems")).build();

			Employee emp3 = Employee.builder().firstName("Gopi").lastName("Battu").email("gopi@abc.com")
					.permanentAddress(new Address("Bangalore", "Karnataka", "India"))
					.temporaryAddress(new Address("Chennai", "Tamilnadu", "India"))
					.department(new Department("Infrastructure Services")).build();

			empRepo.saveAll(Arrays.asList(emp1, emp2, emp3));

		};
	}

}
