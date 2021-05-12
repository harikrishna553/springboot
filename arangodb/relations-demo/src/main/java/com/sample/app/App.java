package com.sample.app;

import java.util.Collection;

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

			WorkingIn workingInd1 = new WorkingIn(emp1, pjt1);
			WorkingIn workingInd2 = new WorkingIn(emp1, pjt2);
			WorkingIn workingInd3 = new WorkingIn(emp2, pjt1);
			WorkingIn workingInd4 = new WorkingIn(emp3, pjt3);
			WorkingIn workingInd5 = new WorkingIn(emp4, pjt3);
			WorkingIn workingInd6 = new WorkingIn(emp5, pjt1);

			workingInRepository.save(workingInd1);
			workingInRepository.save(workingInd2);
			workingInRepository.save(workingInd3);
			workingInRepository.save(workingInd4);
			workingInRepository.save(workingInd5);
			workingInRepository.save(workingInd6);

			Iterable<Employee> empsIterable = employeeRepo.findAll();

			System.out.println("Employees and the projects they are working in\n");
			for (Employee emp : empsIterable) {

				System.out.println("(" + emp.getFirstName() + "," + emp.getLastName() + ") is working in");
				Collection<Project> pjts = emp.getProjects();

				if (pjts == null || pjts.isEmpty()) {
					System.out.println("\tNot working in any project");
				} else {
					for (Project pjt : pjts) {
						System.out.println("\t" + pjt.getProjectName());
					}
				}

			}

			System.out.println("\n\nProjects and the employees working in\n");
			Iterable<Project> pjtsIterable = projectRepo.findAll();

			for (Project pjt : pjtsIterable) {
				System.out.println("Employees working in the project : " + pjt.getProjectName());

				for (Employee emp : pjt.getEmployees()) {
					System.out.println("\t(" + emp.getFirstName() + "," + emp.getLastName() + ")");
				}
			}

		};
	}
}