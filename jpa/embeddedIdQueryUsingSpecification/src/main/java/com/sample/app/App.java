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

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProjectRepository projectRepository) {
		return (args) -> {
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

			Project pjt1 = new Project("Chat Server", "IT", timeStamp, "y", "To chat with people");
			Project pjt2 = new Project("Cabin Control Engine", "IT Services", timeStamp, "y",
					"To main cabin temperature and pressure");
			Project pjt3 = new Project("Advanced Search Engine", "Research Labs", timeStamp, "y",
					"Search based on user profile");
			Project pjt4 = new Project("CRM", "IT Services", timeStamp, "y", "Customer Relationship management");
			
			projectRepository.saveAll(Arrays.asList(pjt1, pjt2, pjt3, pjt4));

		};
	}
}