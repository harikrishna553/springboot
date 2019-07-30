package com.sample.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	@Before("@annotation(com.sample.app.annotations.Loggable)")
	public void logMe(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("Calling " + methodName + " method");
	}

}


