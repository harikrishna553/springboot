package com.sample.app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	@Around("@annotation(com.sample.app.annotations.Loggable)")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Execution started");
		
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Total time taken is : " + (endTime-startTime) + " milliseconds");
		
		System.out.println("Execution Finished");
		
		return result;
	}

}
