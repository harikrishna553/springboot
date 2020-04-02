package com.sample.app.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.app.listener.MyJobExecutionListener;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					System.out.println("Executing step1");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

		return jobBuilderFactory.get("My-First-Job").listener(new MyJobExecutionListener()).start(step1()).build();
	}

}

