package com.sample.app;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.model.IEmployee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	public void printEmployees(Iterable<Employee> emps, String msg) {
		System.out.println(msg);
		for (Employee emp : emps) {
			System.out.println(emp);
		}

		System.out.println();
	}

	public void printIEmployees(Iterable<IEmployee> emps, String msg) {
		System.out.println(msg);
		for (IEmployee emp : emps) {
			System.out.println(emp.getId() + " , " + emp.getFirst_name() + " , " + emp.getLast_name());
		}

		System.out.println();
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").age(32).salary(100000.23).build();
			Employee emp2 = Employee.builder().firstName("Ram").lastName("Chelli").age(43).salary(60000).build();
			Employee emp3 = Employee.builder().firstName("Gopi").lastName("Battu").age(45).salary(1000000).build();
			Employee emp4 = Employee.builder().firstName("Ram").lastName("Srikanth").age(39).salary(60000).build();
			Employee emp5 = Employee.builder().firstName("Surendra").lastName("Sami").age(32).salary(100000.23).build();
			Employee emp6 = Employee.builder().firstName("Deeraj").lastName("Arora").age(41).salary(1000000.23).build();
			Employee emp7 = Employee.builder().firstName("Sailu").lastName("Ptr").age(31).salary(50000.23).build();
			Employee emp8 = Employee.builder().firstName("Loopa").lastName("Battu").age(32).salary(80000.23).build();
			Employee emp9 = Employee.builder().firstName("Ram").lastName("Zlam").age(33).salary(80000.23).build();

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);
			employeeRepository.save(emp4);
			employeeRepository.save(emp5);
			employeeRepository.save(emp6);
			employeeRepository.save(emp7);
			employeeRepository.save(emp8);
			employeeRepository.save(emp9);

			List<Employee> emps = employeeRepository.findAll();
			printEmployees(emps, "All the employees information");

			List<IEmployee> iEmps = employeeRepository.findAllEmpsViaNativeQuery();
			printIEmployees(iEmps, "All the employees information using native query");

			iEmps = employeeRepository.findAllEmpsByFirstNameViaNativeQuery("Ram");
			printIEmployees(iEmps, "All the employees with firstName 'Ram'");

		};
	}

}