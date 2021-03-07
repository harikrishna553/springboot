package com.sample.app.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sample.app.util.TimerUtil;

public class WelcomeJob extends QuartzJobBean {

	@Autowired
	private TimerUtil timerUtil;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String jobKeyName = context.getJobDetail().getKey().getName();
		
		System.out.println(timerUtil.getDate() + " -> " + jobKeyName + " -> Hello World");
	}

}
