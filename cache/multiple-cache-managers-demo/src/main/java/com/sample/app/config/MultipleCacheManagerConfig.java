package com.sample.app.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class MultipleCacheManagerConfig {
	@Bean(name = "caffeineCacheManager")
	@Primary
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("myEmployeeCache");
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine caffeineCacheBuilder() {
		return Caffeine.newBuilder().initialCapacity(100).maximumSize(500).expireAfterWrite(10, TimeUnit.SECONDS)
				.recordStats();
	}

	@Bean(name = "concurrentMapCacheManager")
	public CacheManager alternateCacheManager() {
		return new ConcurrentMapCacheManager("myOrgCache");
	}
}