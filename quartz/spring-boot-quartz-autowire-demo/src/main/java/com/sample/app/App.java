package com.sample.app;

import java.util.Date;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.jobs.WelcomeJob;

@SpringBootApplication
public class App {

	@Autowired
	private Scheduler scheduler;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			// To clean all the existing tables.
			scheduler.clear();

			JobDetailImpl jobDetail1 = new JobDetailImpl();
			jobDetail1.setName("First Job");
			jobDetail1.setJobClass(WelcomeJob.class);
			jobDetail1.setDescription("Simple Task Application");
			jobDetail1.setRequestsRecovery(true);
			jobDetail1.setDurability(true);
			jobDetail1.setKey(new JobKey("Job1"));

			CronTriggerImpl cronTrigger1 = new CronTriggerImpl();
			cronTrigger1.setStartTime(new Date(System.currentTimeMillis() + 1000));
			cronTrigger1.setCronExpression("0/3 * * * * ?");
			cronTrigger1.setName("FirstTrigger");
			cronTrigger1.setDescription("Simple Trigger");

			JobDetailImpl jobDetail2 = new JobDetailImpl();
			jobDetail2.setName("First Job");
			jobDetail2.setJobClass(WelcomeJob.class);
			jobDetail2.setDescription("Simple Task Application");
			jobDetail2.setRequestsRecovery(true);
			jobDetail2.setDurability(true);
			jobDetail2.setKey(new JobKey("Job2"));

			CronTriggerImpl cronTrigger2 = new CronTriggerImpl();
			cronTrigger2.setStartTime(new Date(System.currentTimeMillis() + 1000));
			cronTrigger2.setCronExpression("0/3 * * * * ?");
			cronTrigger2.setName("SecondTrigger");
			cronTrigger2.setDescription("Simple Trigger");

			scheduler.scheduleJob(jobDetail1, cronTrigger1);
			scheduler.scheduleJob(jobDetail2, cronTrigger2);

		};
	}
}
