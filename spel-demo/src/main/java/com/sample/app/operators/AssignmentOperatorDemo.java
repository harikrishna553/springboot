package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Employee;

public class AssignmentOperatorDemo {
	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		Employee emp1 = new Employee();

		String firstName = spelExpressionParser.parseExpression("firstName = 'Krishna'").getValue(emp1, String.class);
		String lastName = spelExpressionParser.parseExpression("lastName = 'Gurram'").getValue(emp1, String.class);
		Integer id = spelExpressionParser.parseExpression("id = 123").getValue(emp1, Integer.class);

		System.out.println("firstName : " + firstName);
		System.out.println("lastName : " + lastName);
		System.out.println("id : " + id);
		System.out.println("emp1 : " + emp1);

	}
}
