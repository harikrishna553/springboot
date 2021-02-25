package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }
	
	
	 @Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
	        return (args) -> {
	            Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").build();
	            Employee emp2 = Employee.builder().firstName("Rahim").lastName("Khan").build();
	            Employee emp3 = Employee.builder().firstName("Robert").lastName("Chelli").build();
	            Employee emp4 = Employee.builder().firstName("Sailaja").lastName("Navakotla").build();
	            Employee emp5 = Employee.builder().firstName("Siva").lastName("Ponnam").build();

	            List<Employee> emps = Arrays.asList(emp1, emp2, emp3, emp4, emp5);

	            employeeRepository.saveAll(emps);


	        };
	    }
}
