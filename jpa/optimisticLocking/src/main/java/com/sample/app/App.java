package com.sample.app;

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
	public CommandLineRunner demo(EmployeeRepository empRepo) {
		return (args) -> {
			Employee emp1 = new Employee("Naresh", "Deva");
			Employee emp2 = new Employee("Nani", "Kulkarni");
			Employee emp3 = new Employee("Ram", "Kishore");
			
			empRepo.save(emp1);
			empRepo.save(emp2);
			Employee empToUpdate = empRepo.save(emp3);
			
			empRepo.findAll().forEach(System.out::println);
			
			System.out.println("\nUpdating the employee\n");
			empToUpdate.setLastName("Manohar");
			empToUpdate = empRepo.save(empToUpdate);
			System.out.println(empToUpdate);
			
			System.out.println("\nUpdating the employee again\n");
			empToUpdate.setLastName("Parikar");
			empToUpdate = empRepo.save(empToUpdate);
			System.out.println(empToUpdate);

		};
	}

}
