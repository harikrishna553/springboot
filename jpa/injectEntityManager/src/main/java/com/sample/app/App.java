package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sample.app.dao.EmployeeDao;
import com.sample.app.model.Employee;

@SpringBootApplication
@EnableJpaAuditing
public class App {

	@Autowired
	private EmployeeDao empDao;
	
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
		
			Employee emp1 = new Employee();
			emp1.setFirstName("Sandeep");
			emp1.setLastName("Maj");
			
			Employee emp2 = new Employee();
			emp2.setFirstName("Swaroop");
			emp2.setLastName("kdp");
			
			empDao.save(emp1);
			empDao.save(emp2);
			
			empDao.all().forEach(System.out::println);
			
		};
	}

}
