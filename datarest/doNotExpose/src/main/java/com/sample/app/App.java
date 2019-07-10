package com.sample.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Address;
import com.sample.app.model.Employee;
import com.sample.app.repository.AddressRepository;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").age(32).salary(100000.23).build();
			Employee emp2 = Employee.builder().firstName("Joel").lastName("Chelli").age(43).salary(60000).build();
			Employee emp3 = Employee.builder().firstName("Gopi").lastName("Battu").age(45).salary(1000000).build();
			Employee emp4 = Employee.builder().firstName("Bomma").lastName("Srikanth").age(39).salary(60000).build();
			Employee emp5 = Employee.builder().firstName("Surendra").lastName("Sami").age(32).salary(100000.23).build();

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);
			employeeRepository.save(emp4);
			employeeRepository.save(emp5);

			Address address1 = Address.builder().street("Chamundi Street").city("Bangalore").street("Karnataka")
					.city("India").pinCode("523134").build();
			Address address2 = Address.builder().street("Banasawadi").city("Hyderabad").street("Andhra Pradesh")
					.city("India").pinCode("524334").build();
			Address address3 = Address.builder().street("Nehru Peta").city("Nuzvid").street("Andhra Pradesh")
					.city("India").pinCode("253134").build();
			Address address4 = Address.builder().street("Chamundi Street").city("Bangalore").street("Karnataka")
					.city("India").pinCode("532134").build();

			addressRepository.save(address1);
			addressRepository.save(address2);
			addressRepository.save(address3);
			addressRepository.save(address4);

		};
	}
}
