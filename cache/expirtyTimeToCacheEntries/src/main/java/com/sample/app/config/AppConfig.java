package com.sample.app.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.sample.app.generator.CacheKeyGenerator;

@Configuration
@EnableCaching
public class AppConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS)
						.maximumSize(100).build().asMap(), false);
			}
		};

		cacheManager.setCacheNames(Arrays.asList("myOrgCache", "myEmployeeCache"));
		return cacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new CacheKeyGenerator();
	}
}
