package com.sample.app.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sample.app.service.DBService;

@Component
@Scope("prototype")
public class AsyncDataPublisherTask implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncDataPublisherTask.class);

	@Autowired
	private DBService dbService;

	private String msg;

	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public void run() {
		LOG.info("thread: {}, is publishing the msg {} to database", Thread.currentThread().getName(), msg );
		dbService.save(msg);
		LOG.info("thread: {}, is published the msg {} to database", Thread.currentThread().getName(),  msg);

	}

}
