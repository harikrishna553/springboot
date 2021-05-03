package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InstanceOfOperatorDemo {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		boolean result1 = spelExpressionParser.parseExpression("'hello' instanceof T(Integer)").getValue(Boolean.class);

		boolean result2 = spelExpressionParser.parseExpression("'hello' instanceof T(String)").getValue(Boolean.class);

		System.out.println(result1);
		System.out.println(result2);
	}
}
