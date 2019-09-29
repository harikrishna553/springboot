package com.sample.app;

import java.util.concurrent.TimeUnit;

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
	private EmployeeRepository empRepo;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	private static int objectIdToUpdate = -1;

	public void sleepNSeconds(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Thread t1 = new Thread() {
		public void run() {
			Employee emp = empRepo.findById(objectIdToUpdate).get();
			System.out.println(getName() + " read the object " + emp);

			sleepNSeconds(3);

			System.out.println(getName() + " Updating the employee\n");
			emp.setLastName("Manohar");
			emp = empRepo.save(emp);
			System.out.println(emp);
			System.out.println(getName() + " Updated the employee\n");
		}
	};

	Thread t2 = new Thread() {
		public void run() {
			Employee emp = empRepo.findById(objectIdToUpdate).get();
			System.out.println(getName() + " read the object " + emp);

			sleepNSeconds(5);

			System.out.println(getName() + " Updating the employee\n");
			emp.setLastName("Krishna");
			emp = empRepo.save(emp);
			System.out.println(emp);
			System.out.println(getName() + " Updated the employee\n");
		}
	};

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			Employee emp1 = new Employee("Naresh", "Deva");
			Employee emp2 = new Employee("Nani", "Kulkarni");
			Employee emp3 = new Employee("Ram", "Kishore");

			empRepo.save(emp1);
			empRepo.save(emp2);
			Employee empToUpdate = empRepo.save(emp3);
			objectIdToUpdate = empToUpdate.getId();

			empRepo.findAll().forEach(System.out::println);

			t1.setName("Thread-1");
			t2.setName("Thread-2");

			t1.start();
			t2.start();

			t1.join();
			t2.join();

		};
	}

}
