package com.sample.app;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Project;
import com.sample.app.repository.ProjectRepository;

@SpringBootApplication
public class App {

	public void printProjects(Iterable<Project> pjts, String msg) {
		System.out.println(msg);
		for (Project pjt : pjts) {
			System.out.println(pjt.getProjectId().getProjectName() + ", " + pjt.getProjectId().getOrganization());
		}

		System.out.println();
	}

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProjectRepository projectRepository) {
		return (args) -> {
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

			Project pjt1 = new Project("Chat Server", "Aero", timeStamp, "y", "To chat with people");
			Project pjt2 = new Project("Cabin Control Engine", "IT Services", timeStamp, "y",
					"To main cabin temperature and pressure");
			Project pjt3 = new Project("Chat Server", "Research Labs", timeStamp, "y", "Search based on user profile");
			Project pjt4 = new Project("CRM", "IT Services", timeStamp, "y", "Customer Relationship management");

			projectRepository.saveAll(Arrays.asList(pjt1, pjt2, pjt3, pjt4));

			Iterable<Project> projects = projectRepository.findAll();
			printProjects(projects, "All Projects");

			projects = projectRepository.findByProjectId_Organization("IT Services");
			printProjects(projects, "All Projects in the organization IT Services");

			projects = projectRepository.findByProjectId_ProjectName("Chat Server");
			printProjects(projects, "All Projects with name Chat Server");
			
			projects = projectRepository.findByProjectId_ProjectNameAndProjectId_Organization("Chat Server", "Aero");
			printProjects(projects, "All Projects with name Chat Server and organization Aero");
			
			

		};
	}
}