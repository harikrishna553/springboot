package com.sample.app;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
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
	private CacheManager caffeineCacheManager;

	@Autowired
	@Qualifier("concurrentMapCacheManager")
	private CacheManager concurrentMapCacheManager;

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
			getEmployeeAndOrgDetails();

			printNativeCache(caffeineCacheManager);
			printNativeCache(concurrentMapCacheManager);

		};
	}

	public void printNativeCache(CacheManager cacheManager) {
		System.out.println("\n**************************************");
		Collection<String> cacheNames = cacheManager.getCacheNames();

		for (String cacheName : cacheNames) {
			System.out.println("\nFor the cache : " + cacheName);

			Map<Object, Object> map = null;
			if (cacheManager.getCache(cacheName) instanceof CaffeineCache) {
				System.out.println("Data from CaffeineCache");
				CaffeineCache cache = (CaffeineCache) cacheManager.getCache(cacheName);
				com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = cache.getNativeCache();
				map = nativeCache.asMap();
			} else {
				System.out.println("Data from ConcurrentMapCache");
				ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache) cacheManager.getCache(cacheName);
				map = concurrentMapCache.getNativeCache();
			}

			for (Object key : map.keySet()) {
				System.out.println(key + " -> " + map.get(key));
			}

		}

		System.out.println("**************************************\n");
	}
}