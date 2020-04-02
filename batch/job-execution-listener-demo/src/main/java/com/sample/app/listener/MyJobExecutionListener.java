package com.sample.app.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobExecutionListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		String jobName = jobExecution.getJobInstance().getJobName();
		
		System.out.println("About to execute the job : " + jobName);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		String jobName = jobExecution.getJobInstance().getJobName();
		
		System.out.println(jobName + " finished execution");
	}

}
