package com.sample.app.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async("myAsyncExecutor")
	public CompletableFuture<String> getMessage1() throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + " executing getMessage1() method");
		TimeUnit.SECONDS.sleep(5);

		return CompletableFuture.completedFuture("Hello");
	}

	@Async("myAsyncExecutor")
	public CompletableFuture<String> getMessage2() throws InterruptedException {

		System.out.println(Thread.currentThread().getName() + " executing getMessage2() method");
		TimeUnit.SECONDS.sleep(5);

		return CompletableFuture.completedFuture("World");
	}
}
