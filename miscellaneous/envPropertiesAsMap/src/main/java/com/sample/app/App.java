package com.sample.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private Environment env;

	public static void main(String args[]) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

	}

	public Map<String, Object> configs() {
		Map<String, Object> properties = new HashMap<>();

		Iterator iterator = ((AbstractEnvironment) env).getPropertySources().iterator();

		while (iterator.hasNext()) {
			PropertySource propertySource = (PropertySource) iterator.next();

			if (propertySource instanceof MapPropertySource) {

				String[] propertyNames = ((MapPropertySource) propertySource).getPropertyNames();

				for (String propName : propertyNames) {
					properties.put(propName, propertySource.getProperty(propName));
				}

			}

		}

		return properties;
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> map = configs();

		System.out.println("****************************************");
		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("****************************************");
	}

}