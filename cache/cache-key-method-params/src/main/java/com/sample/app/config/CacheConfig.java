package com.sample.app.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("myOrgCache", "myEmployeeCache");
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine caffeineCacheBuilder() {
		return Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(10, TimeUnit.SECONDS)
				.recordStats();
	}

}
