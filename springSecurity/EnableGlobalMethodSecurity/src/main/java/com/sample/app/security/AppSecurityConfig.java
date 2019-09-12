package com.sample.app.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/", "/public/*", "/css/*", "/js/*").permitAll()
				.anyRequest().authenticated().and().httpBasic();

	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails krishnas = User.withDefaultPasswordEncoder().username("krishna").password("password123")
				.roles("USER", "ADMIN").build();
		UserDetails rams = User.withDefaultPasswordEncoder().username("rama").password("password123").roles("USER")
				.build();

		List<UserDetails> userDetails = Arrays.asList(krishnas, rams);

		return new InMemoryUserDetailsManager(userDetails);
	}
}