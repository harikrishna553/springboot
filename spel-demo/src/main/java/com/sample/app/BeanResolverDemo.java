package com.sample.app;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.sample.app.model.Employee;

public class BeanResolverDemo {
	public static void main(String[] args) {

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new MyBeanResolver());
		context.setVariable("emp", Employee.class);
	
		Employee emp = (Employee) spelExpressionParser.parseExpression("@emp").getValue(context);

		System.out.println(emp);

	}
}
