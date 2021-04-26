package com.sample.app.generator;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component("myCacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder sb = new StringBuilder();

		if (target != null) {
			sb.append(target.getClass().getSimpleName()).append("-");
		}

		if (method != null) {
			sb.append(method.getName());
		}

		if (params != null) {
			for (Object param : params) {
				sb.append("-").append(param.getClass().getSimpleName()).append(":").append(param);
			}
		}
		return sb.toString();
	}

}