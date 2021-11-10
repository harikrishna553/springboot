package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.components.ListUtil;

@SpringBootApplication
public class App {
	
	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			ListUtil listUtil = new ListUtil();
			autowireCapableBeanFactory.autowireBean(listUtil);

			List<String> uppercaseStrings = listUtil.toUpper(Arrays.asList("One", "two", "three"));
			uppercaseStrings.stream().forEach(System.out::println);

		};
	}

}
