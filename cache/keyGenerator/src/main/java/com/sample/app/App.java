package com.sample.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			printNativeCache();
			
			Employee emp = empService.getEmployeeById(1);
			System.out.println(emp);
			emp = empService.getEmployeeByFirstName("Krishna");
			System.out.println(emp);

			printNativeCache();
			
			emp = empService.getEmployeeById(1);
			System.out.println(emp);
			emp = empService.getEmployeeByFirstName("Krishna");
			System.out.println(emp);
			
			emp = empService.getEmployeeById(1);
			System.out.println(emp);
			emp = empService.getEmployeeByFirstName("Krishna");
			System.out.println(emp);
		};
	}

	public void printNativeCache() {
		System.out.println("\n**************************************");
		Cache cache = cacheManager.getCache("myEmployeeCache");
		System.out.println("-- native cache --");
		Map<String, Object> map = (Map<String, Object>) cache.getNativeCache();
		map.forEach((key, value) -> {
			System.out.println(key + " = " + value);
		});
		System.out.println("**************************************\n");
	}
}
