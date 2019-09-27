package com.sample.app.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AppConfiguration {

	private static final int NUMber_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

	@Bean("appThredPool")
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		threadPoolExecutor.setCorePoolSize(NUMber_OF_PROCESSORS * 5);
		threadPoolExecutor.setMaxPoolSize(NUMber_OF_PROCESSORS * 50);
		threadPoolExecutor.setThreadNamePrefix("BackGround_ThreadPool");
		threadPoolExecutor.initialize();
		return threadPoolExecutor;
	}

}
