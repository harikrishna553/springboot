package com.sample.app.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfig {

	@Bean("myAsyncExecutor")
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		int numProcessors = Runtime.getRuntime().availableProcessors();
		threadPoolExecutor.setCorePoolSize(numProcessors * 10);
		threadPoolExecutor.setMaxPoolSize(numProcessors * 100);
		threadPoolExecutor.setThreadNamePrefix("myAsyncExecutor");
		threadPoolExecutor.initialize();
		return threadPoolExecutor;
	}
}
