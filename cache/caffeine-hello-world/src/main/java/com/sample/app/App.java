package com.sample.app;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.model.Organization;
import com.sample.app.service.EmployeeService;
import com.sample.app.service.OrganizationService;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private OrganizationService orgService;

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void getEmployeeAndOrgDetails() {

		Employee emp = empService.getEmployeeById(1);
		// System.out.println(emp);
		emp = empService.getEmployeeByFirstName("Krishna");
		// System.out.println(emp);

		Organization org = orgService.getById(3);
		// System.out.println(org);
		org = orgService.getByName("GHI Corp");
		// System.out.println(org);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			printNativeCache();
			getEmployeeAndOrgDetails();

			printNativeCache();
			// getEmployeeAndOrgDetails();

			System.out.println("\nSleeping for 5 seconds");
			TimeUnit.SECONDS.sleep(5);
			printNativeCache();

			System.out.println("\nSleeping for 5 seconds");
			TimeUnit.SECONDS.sleep(5);
			printNativeCache();

		};
	}

	public void printNativeCache() {
		System.out.println("\n**************************************");
		System.out.println("-- native cache --");
		Collection<String> cacheNames = cacheManager.getCacheNames();

		for (String cacheName : cacheNames) {
			System.out.println("\nFor the cache : " + cacheName);
			CaffeineCache cache = (CaffeineCache) cacheManager.getCache(cacheName);
			com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = cache.getNativeCache();
			Map<Object, Object> map = nativeCache.asMap();

			for (Object key : map.keySet()) {
				System.out.println(key + " -> " + map.get(key));
			}

		}

		System.out.println("**************************************\n");
	}
}