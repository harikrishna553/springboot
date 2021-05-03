package com.sample.app;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import com.sample.app.model.Employee;

public class ReferVariableDemo {
	public static void main(String[] args) {
		EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
		context.setVariable("newFirstName", "Bhadri");
		context.setVariable("newLastName", "Venkata");
		context.setVariable("newId", 123);
		
		ExpressionParser spelExpressionParser = new SpelExpressionParser();
		
		Employee emp1 = new Employee();

		spelExpressionParser.parseExpression("firstName = #newFirstName").getValue(context, emp1);
		spelExpressionParser.parseExpression("lastName = #newLastName").getValue(context, emp1);
		spelExpressionParser.parseExpression("id = #newId").getValue(context, emp1);
		
		System.out.println(emp1);
	}
}