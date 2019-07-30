package com.sample.app.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	@Pointcut("@annotation(com.sample.app.annotations.Loggable)")
	public void executionTimeExpr() {
	}

	@Around("executionTimeExpr()")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();

		System.out.println("Execution of the method " + methodName + " started.");

		long startTime = System.currentTimeMillis();

		// Proceed with the next advice or target method invocation
		Object result = joinPoint.proceed();

		long endTime = System.currentTimeMillis();

		System.out.println("Execution of the method " + methodName + " finished.");
		System.out.println("Execution of the method takes " + (endTime - startTime) + " milliseconds.");

		return result;
	}

}
