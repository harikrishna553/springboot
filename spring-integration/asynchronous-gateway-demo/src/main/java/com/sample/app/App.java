package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sample.app.gateway.CustomGateway;

@SpringBootApplication
@Configuration
public class App {

	@Autowired
	private CustomGateway customGateway;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			for (int i = 0; i < 5; i++) {
				ListenableFuture<String> reply = customGateway.sendToPrintChannel("Msg " + i);

				reply.addCallback(new ListenableFutureCallback<String>() {

					@Override
					public void onSuccess(String result) {
						System.out.println(result);
					}

					@Override
					public void onFailure(Throwable ex) {
						ex.printStackTrace();
					}
				});
			}

		};

	};

}

