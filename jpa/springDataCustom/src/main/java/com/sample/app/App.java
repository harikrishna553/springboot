package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = new Employee(1, "Lahari", "Gurram");
			Employee emp2 = new Employee(2, "bala", "Ponnam");
			Employee emp3 = new Employee(3, "Chandu", "Dondapati");
			Employee emp4 = new Employee(4, "Sudheer", "Ganji");
			Employee emp5 = new Employee(5, "Shankari", "Sri");
			
			empRepo.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5));
			
			empRepo.emps(Arrays.asList(2, 3, 5)).forEach(System.out::println);;
			
			
		};
	}
}
