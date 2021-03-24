package com.sample.app.interceptor;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Component
public class CacheInterceptor implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.addCacheMapping(CacheControl.maxAge(1, TimeUnit.HOURS).noTransform().mustRevalidate(),
				"/v1/images/intercept/by-name/*");
		registry.addInterceptor(interceptor);
	}
}
