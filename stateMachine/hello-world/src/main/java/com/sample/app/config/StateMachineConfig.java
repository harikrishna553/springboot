package com.sample.app.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.sample.app.model.Events;
import com.sample.app.model.States;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
		config.withConfiguration().autoStartup(true).listener(listener());
	}

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates().initial(States.NEW).states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions.withExternal().source(States.NEW).target(States.READY).event(Events.ADMITTED).and().withExternal()
				.source(States.READY).target(States.RUNNING).event(Events.SCHEDULER_DISPATCH).and().withExternal()
				.source(States.RUNNING).target(States.READY).event(Events.INTERRUPT).and().withExternal()
				.source(States.RUNNING).target(States.WAITING).event(Events.WAIT).and().withExternal()
				.source(States.RUNNING).target(States.EXITED).event(Events.EXIT).and().withExternal()
				.source(States.WAITING).target(States.READY).event(Events.WAIT_EVENT_COMPLETED);
	}

	@Bean
	public StateMachineListener<States, Events> listener() {
		return new StateMachineListenerAdapter<States, Events>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				if(from != null && to != null) {
					System.out.println("State changed from " + from.getId() + " to " + to.getId());
				}
				
			}
		};
	}

}
