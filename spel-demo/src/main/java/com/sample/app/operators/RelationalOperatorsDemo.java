package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class RelationalOperatorsDemo {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expr1 = "2 == 2";
		String expr2 = "2 != 2";
		String expr3 = "2 < 2";
		String expr4 = "2 <= 2";
		String expr5 = "2 > 2";
		String expr6 = "2 >= 2";

		boolean expr1Result = spelExpressionParser.parseExpression(expr1).getValue(Boolean.class);
		boolean expr2Result = spelExpressionParser.parseExpression(expr2).getValue(Boolean.class);
		boolean expr3Result = spelExpressionParser.parseExpression(expr3).getValue(Boolean.class);
		boolean expr4Result = spelExpressionParser.parseExpression(expr4).getValue(Boolean.class);
		boolean expr5Result = spelExpressionParser.parseExpression(expr5).getValue(Boolean.class);
		boolean expr6Result = spelExpressionParser.parseExpression(expr6).getValue(Boolean.class);

		System.out.println(expr1 + " evaluated to " + expr1Result);
		System.out.println(expr2 + " evaluated to " + expr2Result);
		System.out.println(expr3 + " evaluated to " + expr3Result);
		System.out.println(expr4 + " evaluated to " + expr4Result);
		System.out.println(expr5 + " evaluated to " + expr5Result);
		System.out.println(expr6 + " evaluated to " + expr6Result);

	}
}
