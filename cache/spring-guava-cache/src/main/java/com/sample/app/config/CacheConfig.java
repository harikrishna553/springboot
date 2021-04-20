package com.sample.app.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.sample.app.generator.CacheKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public CacheManager cacheManager() {
	
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		GuavaCache myOrgCache = new GuavaCache("myOrgCache", CacheBuilder.newBuilder().build());
		GuavaCache myEmployeeCache = new GuavaCache("myEmployeeCache",
				CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build());
		cacheManager.setCaches(Arrays.asList(myOrgCache, myEmployeeCache));
		return cacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new CacheKeyGenerator();
	}
}