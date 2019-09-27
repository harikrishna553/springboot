package com.sample.app.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {

	private static void sleep(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async("appThredPool")
	public CompletableFuture<String> sayHello() {
		System.out.println("Executing sayHello method");
		sleep(3);
		System.out.println("sayHello method finsihed");
		return CompletableFuture.completedFuture("Hello User!!!!!!");
	}

	@Async("appThredPool")
	public CompletableFuture<String> welcomeUser() {
		System.out.println("Executing welcomeUser method");
		sleep(3);
		System.out.println("welcomeUser method finsihed");
		return CompletableFuture.completedFuture("Welcome User!!!!!!");
	}
}