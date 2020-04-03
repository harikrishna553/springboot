package com.sample.app.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupCodeListener implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		System.out.println("\"Spring Boot Application started succesfully..Executing AppStartupCodeListener code!!!!!\"");
		return;
	}
}