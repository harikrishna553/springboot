package com.sample.app;

import javax.annotation.PostConstruct;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	@Autowired
	private Scheduler scheduler;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void clean() throws SchedulerException {
		// This will delete all the existing jobs on every new run of the application,
		// you can comment this based on your needs
		scheduler.clear();
	}

}
