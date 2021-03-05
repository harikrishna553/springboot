package com.sample.app;

import java.util.Date;

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

			scheduler.clear();
			
			JobDetailImpl jobDetail = new JobDetailImpl();
			jobDetail.setName("First Job");
			jobDetail.setJobClass(WelcomeJob.class);
			jobDetail.setDescription("Simple Task Application");
			jobDetail.setRequestsRecovery(true);
			jobDetail.setDurability(true);

			CronTriggerImpl cronTrigger1 = new CronTriggerImpl();
			cronTrigger1.setStartTime(new Date(System.currentTimeMillis() + 1000));
			cronTrigger1.setCronExpression("0/3 * * * * ?");
			cronTrigger1.setName("FirstTrigger");
			cronTrigger1.setDescription("Simple Trigger");

			scheduler.scheduleJob(jobDetail, cronTrigger1);
			

		};
	}
}
