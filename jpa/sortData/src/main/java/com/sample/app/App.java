package com.sample.app;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort.Direction;

import com.sample.app.model.Employee;
import com.sample.app.repository.EmployeeRepository;

import org.springframework.data.domain.Sort;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
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
			
			//SELECT * FROM my_employee ORDER BY age, first_name;
			Sort sort = new Sort(Direction.ASC, Arrays.asList("age","firstName"));
			
			Iterable<Employee> emps = employeeRepository.findAll(sort);
			
			for(Employee emp: emps) {
				System.out.println(emp);
			}

		};
	}

}
