package com.sample.app;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class HelloWorld {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "'Welcome to SPEL Expression parser'.length";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		Integer stringLength = (Integer) exp.getValue();
		System.out.println(stringLength);

	}
}
