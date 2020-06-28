package com.sample.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.model.Hobby;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository, EmployeeService empService) {
		return (args) -> {

			Hobby hobby1 = new Hobby("Cricket");
			Hobby hobby2 = new Hobby("Blogging");
			Hobby hobby3 = new Hobby("Football");
			Hobby hobby4 = new Hobby("Tennis");
			Hobby hobby5 = new Hobby("Blogging");
			Hobby hobby6 = new Hobby("Cricket");
			Hobby hobby7 = new Hobby("Blogging");
			Hobby hobby8 = new Hobby("Cricket");
			

			Set<Hobby> hobbies1 = new HashSet<>();
			hobbies1.add(hobby1);
			hobbies1.add(hobby2);

			Set<Hobby> hobbies2 = new HashSet<>();
			hobbies2.add(hobby3);
			hobbies2.add(hobby4);

			Set<Hobby> hobbies3 = new HashSet<>();
			hobbies3.add(hobby5);
			hobbies3.add(hobby6);
			
			Set<Hobby> hobbies4 = new HashSet<>();
			hobbies4.add(hobby7);
			hobbies4.add(hobby8);

			Employee emp1 = new Employee("Ram", "Gurram", 32, hobbies1);
			Employee emp2 = new Employee("Gopi", "Battu", 30, hobbies2);
			Employee emp3 = new Employee("Surendra", "Sami", 32, hobbies3);
			Employee emp4 = new Employee("Sai", "Praneet", 30, hobbies4);
			Employee emp5 = new Employee("Sailu", "PTR", 31, null);

			empService.save(emp1);
			empService.save(emp2);
			empService.save(emp3);
			empService.save(emp4);
			empService.save(emp5);

			for (Employee emp : employeeRepository.findAll()) {
				System.out.println(emp);
			}

		};
	}
}