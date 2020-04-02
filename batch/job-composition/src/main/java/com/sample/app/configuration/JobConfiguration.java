package com.sample.app.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
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

	@Autowired
	private JobLauncher jobLauncher;

	@Bean
	public Step parentJobInitializer() {
		return this.stepBuilderFactory.get("Parent-Job-Initializer")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					String stepName = chunkContext.getStepContext().getStepName();

					System.out.println(stepName + " is getting executed!!!");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Step childJob1Step1() {
		return this.stepBuilderFactory.get("ChildJob1-Step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					String stepName = chunkContext.getStepContext().getStepName();

					System.out.println(stepName + " is getting executed!!!");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Job childJob1() {
		return jobBuilderFactory.get("childJob1").start(childJob1Step1()).build();
	}

	@Bean
	public Step childJob2Step1() {
		return this.stepBuilderFactory.get("ChildJob2-Step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					String stepName = chunkContext.getStepContext().getStepName();

					System.out.println(stepName + " is getting executed!!!");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Job childJob2() {
		return jobBuilderFactory.get("childJob2").start(childJob2Step1()).build();
	}

	@Bean
	public Step parentJobCleanup() {
		return this.stepBuilderFactory.get("Parent-Job-Cleanup")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {

					String stepName = chunkContext.getStepContext().getStepName();

					System.out.println(stepName + " is getting executed!!!");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Job parentJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		Step childJob1Step = new JobStepBuilder(new StepBuilder("childJob1")).job(childJob1()).launcher(jobLauncher)
				.repository(jobRepository).transactionManager(platformTransactionManager).build();

		Step childJob2Step = new JobStepBuilder(new StepBuilder("childJob2")).job(childJob2()).launcher(jobLauncher)
				.repository(jobRepository).transactionManager(platformTransactionManager).build();

		return jobBuilderFactory.get("parentJob").start(parentJobInitializer()).next(childJob1Step).next(childJob2Step)
				.next(parentJobCleanup()).build();
	}

}