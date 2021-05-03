package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class LogicalOperatorsDemo {

	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expr1 = "false && false";
		String expr2 = "false && true";
		String expr3 = "true && false";
		String expr4 = "true && true";

		String expr5 = "false || false";
		String expr6 = "false || true";
		String expr7 = "true || false";
		String expr8 = "true || true";

		String expr9 = "!false";
		String expr10 = "!true";

		boolean expr1Result = spelExpressionParser.parseExpression(expr1).getValue(Boolean.class);
		boolean expr2Result = spelExpressionParser.parseExpression(expr2).getValue(Boolean.class);
		boolean expr3Result = spelExpressionParser.parseExpression(expr3).getValue(Boolean.class);
		boolean expr4Result = spelExpressionParser.parseExpression(expr4).getValue(Boolean.class);
		boolean expr5Result = spelExpressionParser.parseExpression(expr5).getValue(Boolean.class);
		boolean expr6Result = spelExpressionParser.parseExpression(expr6).getValue(Boolean.class);
		boolean expr7Result = spelExpressionParser.parseExpression(expr7).getValue(Boolean.class);
		boolean expr8Result = spelExpressionParser.parseExpression(expr8).getValue(Boolean.class);
		boolean expr9Result = spelExpressionParser.parseExpression(expr9).getValue(Boolean.class);
		boolean expr10Result = spelExpressionParser.parseExpression(expr10).getValue(Boolean.class);

		System.out.println(expr1 + " evaluated to " + expr1Result);
		System.out.println(expr2 + " evaluated to " + expr2Result);
		System.out.println(expr3 + " evaluated to " + expr3Result);
		System.out.println(expr4 + " evaluated to " + expr4Result);
		System.out.println(expr5 + " evaluated to " + expr5Result);
		System.out.println(expr6 + " evaluated to " + expr6Result);
		System.out.println(expr7 + " evaluated to " + expr7Result);
		System.out.println(expr8 + " evaluated to " + expr8Result);
		System.out.println(expr9 + " evaluated to " + expr9Result);
		System.out.println(expr10 + " evaluated to " + expr10Result);
	}

}
