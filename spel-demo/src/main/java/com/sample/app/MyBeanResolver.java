package com.sample.app;

import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;

public class MyBeanResolver implements BeanResolver{

	@Override
	public Object resolve(EvaluationContext context, String beanName) throws AccessException {
		
		Class clazz = (Class) context.lookupVariable(beanName);
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	
	}

}
