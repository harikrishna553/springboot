package com.sample.app.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Tasklet task1() {
		Tasklet tasklet = new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution()
						.getExecutionContext();

				if (stepExecutionContext.containsKey("step-successful")) {
					System.out.println("Step 1: Found the key 'step-successful'");
					return RepeatStatus.FINISHED;
				}
				System.out.println("Executing step 1 for first time");
				stepExecutionContext.put("step-successful", 1);
				throw new RuntimeException("Step1 failed to execute");

			}

		};
		return tasklet;
	}

	@Bean
	public Tasklet task2() {
		Tasklet tasklet = new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution()
						.getExecutionContext();

				if (stepExecutionContext.containsKey("step-successful")) {
					System.out.println("Step 2: Found the key 'step-successful'");
					return RepeatStatus.FINISHED;
				}
				System.out.println("Executing step 2 for first time");
				stepExecutionContext.put("step-successful", 1);
				throw new RuntimeException("Step2 failed to execute");

			}

		};
		return tasklet;
	}

	@Bean
	public Step step1() throws Exception {
		return this.stepBuilderFactory.get("step1").tasklet(task1()).build();
	}

	@Bean
	public Step step2() throws Exception {
		return this.stepBuilderFactory.get("step2").tasklet(task2()).build();
	}

	@Bean
	public Job myJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager)
			throws Exception {

		return jobBuilderFactory.get("My-First-Job").start(step1()).next(step2()).build();
	}

}

