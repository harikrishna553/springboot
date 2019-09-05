package com.sample.app.components;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHealth implements HealthIndicator {

	@Override
	public Health health() {
		return Health.down().outOfService().build();
	}

}
