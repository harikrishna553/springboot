package com.sample.app.deciders;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class MyJobExecutionDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

		ExecutionContext jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();

		String data = jobExecutionContext.getString("status");

		if ("passed".equals(data)) {
			return new FlowExecutionStatus("LEFT");
		} else {
			return new FlowExecutionStatus("RIGHT");
		}

	}

}
