package com.sample.app.config;

import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sample.app.customizations.ShellOutputHelper;

@Configuration
public class SpringShellConfig {

	@Bean
	public ShellOutputHelper shellHelper(@Lazy Terminal terminal) {
		return new ShellOutputHelper(terminal);
	}

}