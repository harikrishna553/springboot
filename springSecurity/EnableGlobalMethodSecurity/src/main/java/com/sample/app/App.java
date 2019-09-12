package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.app.model.Project;
import com.sample.app.repository.ProjectRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	ProjectRepository pjtRepo;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Project pjt1 = new Project();
		pjt1.setId(1);
		pjt1.setProjectName("Content Management");

		Project pjt2 = new Project();
		pjt2.setId(2);
		pjt2.setProjectName("Cabin Control");

		pjtRepo.save(pjt1);
		pjtRepo.save(pjt2);
	}
}