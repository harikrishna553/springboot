package com.sample.app.listeners;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCacheEnventListener implements CacheEventListener<Object, Object> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCacheEnventListener.class);

	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		LOGGER.info("\n-----------------------------------------------");
		LOGGER.info("Event received");
		LOGGER.info("event type: {}", cacheEvent.getType());
		LOGGER.info("event key: {}", cacheEvent.getKey());
		LOGGER.info("old value: {}", cacheEvent.getOldValue());
		LOGGER.info("new value: {}", cacheEvent.getNewValue());
		LOGGER.info("-----------------------------------------------");

	}
}