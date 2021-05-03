package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpcifyAnInstanceOfCalss {
	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		Class dateClass = spelExpressionParser.parseExpression("T(java.util.Date)").getValue(Class.class);

		Class stringClass = spelExpressionParser.parseExpression("T(String)").getValue(Class.class);
		
		Class empClass = spelExpressionParser.parseExpression("T(com.sample.app.model.Employee)").getValue(Class.class);
		
		System.out.println("dateClass : " + dateClass.getCanonicalName());
		System.out.println("stringClass : " + stringClass.getCanonicalName());
		System.out.println("empClass : " + empClass.getCanonicalName());
	}
}
