package com.sample.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PerformanceFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(PerformanceFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long time1 = System.nanoTime();
		chain.doFilter(request, response);
		long time2 = System.nanoTime();

		logger.info("Total time taken: {} nano seconds", (time2 - time1));

	}

}
