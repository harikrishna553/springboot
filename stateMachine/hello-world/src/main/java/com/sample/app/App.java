package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;

import com.sample.app.model.Events;
import com.sample.app.model.States;

@SpringBootApplication
public class App {
	@Autowired
	private StateMachine<States, Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			stateMachine.sendEvent(Events.ADMITTED);
		    stateMachine.sendEvent(Events.SCHEDULER_DISPATCH);
		    stateMachine.sendEvent(Events.INTERRUPT);
		    stateMachine.sendEvent(Events.SCHEDULER_DISPATCH);
		    stateMachine.sendEvent(Events.WAIT);
		    stateMachine.sendEvent(Events.WAIT_EVENT_COMPLETED);
		    stateMachine.sendEvent(Events.SCHEDULER_DISPATCH);
		    stateMachine.sendEvent(Events.EXIT);
		};
	}

}
