package com.sample.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.generator.CacheKeyGenerator;

@Configuration
@EnableCaching
public class AppConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public CacheManager cacheManager() {
		List<ConcurrentMapCache> caches = Arrays.asList(new ConcurrentMapCache("myEmployeeCache"),
				new ConcurrentMapCache("myOrgCache"));
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new CacheKeyGenerator();
	}
}