package com.sample.app.controller;

import java.text.ParseException;
import java.util.Date;

import javax.validation.Valid;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.jobs.WelcomeJob;
import com.sample.app.model.JobRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/jobs")
@Api(tags = { "This section contains all Jobs Speicifc Operations" })
public class JobController {

	@Autowired
	private Scheduler scheduler;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create new job", notes = "Create new job")
	public ResponseEntity<Date> create(@RequestBody @Valid JobRequestDto jobRequestDto)
			throws ParseException, SchedulerException {

		JobDetailImpl jobDetail1 = new JobDetailImpl();
		jobDetail1.setName(jobRequestDto.getJobName());
		jobDetail1.setJobClass(WelcomeJob.class);
		jobDetail1.setDescription(jobRequestDto.getJobDescription());
		jobDetail1.setRequestsRecovery(true);
		jobDetail1.setDurability(true);
		jobDetail1.setKey(new JobKey(jobRequestDto.getJobName()));

		CronTriggerImpl cronTrigger1 = new CronTriggerImpl();
		cronTrigger1.setStartTime(new Date(System.currentTimeMillis() + 1000));
		cronTrigger1.setCronExpression(jobRequestDto.getCronExpression());
		cronTrigger1.setName(jobRequestDto.getJobName());
		cronTrigger1.setDescription(jobRequestDto.getJobDescription());

		Date date = scheduler.scheduleJob(jobDetail1, cronTrigger1);

		return ResponseEntity.status(HttpStatus.CREATED).body(date);

	}

	@ApiOperation(value = "Delete a job", notes = "Delete a job")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@RequestParam(name = "jobName", required = true) String jobName)
			throws SchedulerException {

		JobKey jobKey = new JobKey(jobName);
		Boolean isJobDeleted = scheduler.deleteJob(jobKey);
		return ResponseEntity.status(HttpStatus.OK).body(isJobDeleted);

	}

}
