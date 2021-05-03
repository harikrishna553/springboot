package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InvokeStaticMethodDemo {
	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();
		
		String expr1 = "T(java.lang.Math).random() * 100.0 ";
		
		Double randValue = spelExpressionParser.parseExpression(expr1).getValue(Double.class);
		
		System.out.println("randValue : " + randValue);
	}

}
