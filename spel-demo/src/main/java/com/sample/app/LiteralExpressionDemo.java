package com.sample.app;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class LiteralExpressionDemo {

	public static void main(String args[]) {
		ExpressionParser parser = new SpelExpressionParser();

		String helloWorld = parser.parseExpression("'Hello World'").getValue(String.class);
		String helloWorldInQuotes = parser.parseExpression("'''Hello World'''").getValue(String.class);

		double realValue = parser.parseExpression("3.14E+23").getValue(Double.class);

		int maxValue = parser.parseExpression("0x7FFFFFFF").getValue(Integer.class);

		boolean trueValue = parser.parseExpression("true").getValue(Boolean.class);

		Object nullValue = parser.parseExpression("null").getValue();

		System.out.println("helloWorld : " + helloWorld);
		System.out.println("helloWorldInQuotes : " + helloWorldInQuotes);
		System.out.println("realValue : " + realValue);
		System.out.println("maxValue : " + maxValue);
		System.out.println("trueValue : " + trueValue);
		System.out.println("nullValue : " + nullValue);

	}

}
