package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class App {
	@Autowired
	private StateMachine<String, String> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			stateMachine.sendEvent("ADMITTED");
			stateMachine.sendEvent("SCHEDULER_DISPATCH");
			stateMachine.sendEvent("INTERRUPT");
			stateMachine.sendEvent("SCHEDULER_DISPATCH");
			stateMachine.sendEvent("WAIT");
			stateMachine.sendEvent("WAIT_EVENT_COMPLETED");
			stateMachine.sendEvent("SCHEDULER_DISPATCH");
			stateMachine.sendEvent("EXIT");
		};
	}

}
