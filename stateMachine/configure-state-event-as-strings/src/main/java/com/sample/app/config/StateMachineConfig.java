package com.sample.app.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {
	@Override
	public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
		config.withConfiguration().autoStartup(true).listener(listener());
	}

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
		states.withStates().initial("NEW")
				.states(new HashSet<String>(Arrays.asList("NEW", "READY", "RUNNING", "WAITING", "EXITED")));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
		transitions.withExternal()
				.source("NEW").target("READY").event("ADMITTED")
				.and().withExternal()
				.source("READY").target("RUNNING").event("SCHEDULER_DISPATCH")
				.and().withExternal()
				.source("RUNNING").target("READY").event("INTERRUPT")
				.and().withExternal()
				.source("RUNNING").target("WAITING").event("WAIT")
				.and().withExternal()
				.source("RUNNING").target("EXITED").event("EXIT")
				.and().withExternal()
				.source("WAITING").target("READY").event("WAIT_EVENT_COMPLETED");
	}

	@Bean
	public StateMachineListener<String, String> listener() {
		return new StateMachineListenerAdapter<String, String>() {
			@Override
			public void stateChanged(State<String, String> from, State<String, String> to) {
				if (from != null && to != null) {
					System.out.println("State changed from " + from.getId() + " to " + to.getId());
				}

			}
		};
	}

}
