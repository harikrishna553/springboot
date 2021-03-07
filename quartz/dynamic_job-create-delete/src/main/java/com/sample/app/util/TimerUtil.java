package com.sample.app.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimerUtil {
	
	public Date getDate() {
		return new Date();
	}

}
