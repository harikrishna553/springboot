package com.sample.app.configuration;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.deciders.MyJobExecutionDecider;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private MyJobExecutionDecider decider;

	private static Random random = new Random();

	public boolean isTestPassed() {
		return random.nextInt() % 2 == 0;
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("My-Step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					if (isTestPassed()) {
						chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
								.put("status", "passed");
						System.out.println("test1 is passed");
					} else {
						chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
								.put("status", "failed");
						System.out.println("test1 is failed");
					}

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Step step2() {
		return this.stepBuilderFactory.get("My-Step2")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					System.out.println("Executing step 2");
					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Step step3() {
		return this.stepBuilderFactory.get("My-Step3")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Executing step 3");
					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("My-Job").start(step1()).next(decider)
				.from(decider).on("LEFT").to(step2())
				.from(decider).on("RIGHT").to(step3())
				.end().build();
	}

}

