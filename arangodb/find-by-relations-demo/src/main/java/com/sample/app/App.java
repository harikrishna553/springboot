package com.sample.app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.entity.Project;
import com.sample.app.entity.WorkingIn;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.repository.ProjectRepository;
import com.sample.app.repository.WorkingInRepository;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepo, ProjectRepository projectRepo,
			WorkingInRepository workingInRepository) {

		return (args) -> {

			Project pjt1 = new Project(1, "HPHS");
			Project pjt2 = new Project(2, "Employee management system");
			Project pjt3 = new Project(3, "Flight control system");

			pjt1 = projectRepo.save(pjt1);
			pjt2 = projectRepo.save(pjt2);
			pjt3 = projectRepo.save(pjt3);

			Employee emp1 = new Employee(1, "Ram", "Gurram", 31);
			Employee emp2 = new Employee(2, "Rudra", "Gurram", 32);
			Employee emp3 = new Employee(3, "Gopi", "Battu", 31);
			Employee emp4 = new Employee(4, "Joel", "Chelli", 32);
			Employee emp5 = new Employee(5, "Sailu", "Ptr", 31);
			Employee emp6 = new Employee(6, "Lahari", "Gurram", 23);

			emp1 = employeeRepo.save(emp1);
			emp2 = employeeRepo.save(emp2);
			emp3 = employeeRepo.save(emp3);
			emp4 = employeeRepo.save(emp4);
			emp5 = employeeRepo.save(emp5);
			emp6 = employeeRepo.save(emp6);

			WorkingIn workingInd1 = new WorkingIn(emp1, pjt1, 2009, null);
			WorkingIn workingInd2 = new WorkingIn(emp1, pjt2, 2008, 2019);
			WorkingIn workingInd3 = new WorkingIn(emp2, pjt1, 2013, 2021);
			WorkingIn workingInd4 = new WorkingIn(emp3, pjt3, 2018, null);
			WorkingIn workingInd5 = new WorkingIn(emp4, pjt3, 2008, 2013);
			WorkingIn workingInd6 = new WorkingIn(emp5, pjt1, 2011, 2013);

			workingInRepository.save(workingInd1);
			workingInRepository.save(workingInd2);
			workingInRepository.save(workingInd3);
			workingInRepository.save(workingInd4);
			workingInRepository.save(workingInd5);
			workingInRepository.save(workingInd6);

			System.out.println("Employees working in the project HPHS");
			List<Employee> emps = employeeRepo.findByProjectsProjectName("HPHS");

			for (Employee emp : emps) {
				System.out.println(emp);
			}
		};
	}
}