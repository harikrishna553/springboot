package com.sample.app.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {
	
	@Autowired
	private AsyncService asyncService;

	public String getSecretMessage() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future1 = asyncService.getMessage1();
		CompletableFuture<String> future2 = asyncService.getMessage2();

		CompletableFuture.allOf(future1, future2).join();

		String secret = future1.get() + future2.get();

		return secret;
	}

}
