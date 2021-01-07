package com.sample.app.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void crawlWeb() {
		log.info("Crawling web", dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedRate = 10000)
	public void notifyUser() {
		log.info("Notifying user", dateFormat.format(new Date()));
	}
}