package com.sample.app.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	private void sleep(int timeToSleep) {
		try {
			System.out.println(Thread.currentThread().getName() + " going to sleep for " + timeToSleep + " seconds");
			TimeUnit.SECONDS.sleep(timeToSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("My-Step1")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println(chunkContext.getStepContext().getStepName() + " getting executed by "
							+ Thread.currentThread().getName());

					sleep(2);

					System.out.println(chunkContext.getStepContext().getStepName() + " executed by "
							+ Thread.currentThread().getName());

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Step step2() {
		return this.stepBuilderFactory.get("My-Step2")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println(chunkContext.getStepContext().getStepName() + " getting executed by "
							+ Thread.currentThread().getName());

					sleep(2);

					System.out.println(chunkContext.getStepContext().getStepName() + " executed by "
							+ Thread.currentThread().getName());

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Flow flow1() {

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flow1");

		flowBuilder.start(step1()).next(step2()).end();

		return flowBuilder.build();
	}

	@Bean
	public Flow flow2() {

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flow2");

		flowBuilder.start(step1()).next(step2()).end();

		return flowBuilder.build();
	}

	@Bean
	public Flow flow3() {

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flow3");

		flowBuilder.start(step1()).next(step2()).end();

		return flowBuilder.build();
	}

	@Bean
	public Job job() {
		return jobBuilderFactory.get("My-Job").start(flow1()).split(new SimpleAsyncTaskExecutor()).add(flow2(), flow3())
				.end().build();
	}

}
