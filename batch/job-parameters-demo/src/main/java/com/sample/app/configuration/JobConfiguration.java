package com.sample.app.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public Tasklet tasklet1(@Value("#{jobParameters['sleepTime']}") Integer sleepTime) {
		return (StepContribution contribution, ChunkContext chunkContext) -> {

			System.out.println("Going to sleep for " + sleepTime + " seconds");
			TimeUnit.SECONDS.sleep(sleepTime);
			System.out.println("Tasklet resumed from sleep");

			return RepeatStatus.FINISHED;

		};
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").tasklet(tasklet1(null)).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").start(step1()).build();
	}

}


