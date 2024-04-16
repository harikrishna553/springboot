package com.sample.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DBService {
	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);
	
	public void save(String msg) {
		LOG.info("msg {} is saved to database", msg);
	}
	

}
