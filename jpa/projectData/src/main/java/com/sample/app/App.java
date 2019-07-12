package com.sample.app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sample.app.model.Address;
import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeDetails;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
@EnableJpaAuditing
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = new Employee();

			emp1.setFirstName("Hari Krishna");
			emp1.setLastName("Gurram");

			Address addr1 = new Address();
			addr1.setCity("Bangalore");
			addr1.setCountry("India");
			addr1.setPIN("560037");
			addr1.setState("Karnataka");
			addr1.setStreet("Chowdeswari street");

			Address addr2 = new Address();
			addr2.setCity("Ongole");
			addr2.setCountry("India");
			addr2.setPIN("523169");
			addr2.setState("Andhra Pradesh");
			addr2.setStreet("bodhavada");

			emp1.getAddresses().add(addr1);
			emp1.getAddresses().add(addr2);

			employeeRepository.save(emp1);

			Iterable<Employee> emps = employeeRepository.findAll();

			for (Employee emp : emps) {
				System.out.println(emp);
			}
			
			List<EmployeeDetails> empDetails = employeeRepository.getEmployeeDetails();
			for(EmployeeDetails empDetail : empDetails) {
				System.out.println(empDetail);
			}
			
		};
	}

}
