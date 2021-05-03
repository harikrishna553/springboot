package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class RegularExpressionDemo {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		boolean isValidEmail1 = spelExpressionParser.parseExpression("'hello' matches '^[A-Za-z0-9+_.-]+@(.+)$'")
				.getValue(Boolean.class);
		boolean isValidEmail2 = spelExpressionParser.parseExpression("'hello@abc.com' matches '^[A-Za-z0-9+_.-]+@(.+)$'")
				.getValue(Boolean.class);

		System.out.println("Is 'hello' a valid email? : " + isValidEmail1);
		System.out.println("Is 'hello@abc.com' a valid email? : " + isValidEmail2);
	}
}
