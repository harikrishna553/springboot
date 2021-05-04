package com.sample.app;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.sample.app.util.IntegerUtil;

public class RootObjectDemo {

	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(new IntegerUtil());

		String expression = "isEven(10)";
		Boolean result = spelExpressionParser.parseExpression(expression).getValue(context, Boolean.class);

		System.out.println(expression + " " + result);

		expression = "isEven(11)";
		result = spelExpressionParser.parseExpression(expression).getValue(context, Boolean.class);

		System.out.println(expression + " " + result);

	}

}
