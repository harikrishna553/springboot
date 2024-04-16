package com.sample.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sample.app.tasks.AsyncDataPublisherTask;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class App {
	private static final Logger lOG = LoggerFactory.getLogger(App.class);

	private static int count = 0;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Scheduled(fixedDelay = 30 * 1000)
	public void saveMsgs() {
		lOG.info("****Publishing 20 messages to the database in async way****");
		for (int i = 0; i < 20; i++) {
			count++;
			AsyncDataPublisherTask asyncTask = appContext.getBean(AsyncDataPublisherTask.class);
			lOG.info("asyncTask {} is created", asyncTask);
			asyncTask.setMsg("msg->" + count);
			taskExecutor.execute(asyncTask);
		}

	}
}
