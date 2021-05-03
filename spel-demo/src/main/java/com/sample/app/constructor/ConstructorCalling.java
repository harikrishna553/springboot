package com.sample.app.constructor;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Employee;

public class ConstructorCalling {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "new String('Hello World')";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		String str = exp.getValue(String.class);
		System.out.println("str :" + str);

		expressionString = "new String('Hello World').toUpperCase";
		exp = spelExpressionParser.parseExpression(expressionString);
		String strInUppercase = exp.getValue(String.class);
		System.out.println("\nstrInUppercase :" + strInUppercase);

		expressionString = "new com.sample.app.model.Employee(1, 'Krishna', 'Gurram')";
		exp = spelExpressionParser.parseExpression(expressionString);
		Employee emp = exp.getValue(Employee.class);
		System.out.println("\nemp :" + emp);

	}
}
