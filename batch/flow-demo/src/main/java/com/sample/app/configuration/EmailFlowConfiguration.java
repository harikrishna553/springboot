package com.sample.app.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class EmailFlowConfiguration {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step readSubscribers() {
		return this.stepBuilderFactory.get("Read-subscribers")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Flow Step 1: Reading all the subscribers for this job");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Step sendEmail() {
		return this.stepBuilderFactory.get("Send-Email")
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Flow Step 2: Sending email to all subscribers");

					return RepeatStatus.FINISHED;

				}).build();
	}

	@Bean
	public Flow sendNotification() {

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("notification");

		flowBuilder.start(readSubscribers()).next(sendEmail()).end();

		return flowBuilder.build();
	}
}