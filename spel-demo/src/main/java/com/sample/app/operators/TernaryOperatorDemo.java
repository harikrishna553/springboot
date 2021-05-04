package com.sample.app.operators;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.sample.app.util.IntegerUtil;

public class TernaryOperatorDemo {

	public static void main(String[] args) {

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(new IntegerUtil());
		context.setVariable("var1", 5);
		context.setVariable("var2", 6);
	
		String expression = "isEven(#var1)? #var1 + ' is even number'  : #var1 + ' is not even number'";

		String queryResultString = spelExpressionParser.parseExpression(expression).getValue(context, String.class);

		System.out.println(queryResultString);

		
		expression = "isEven(#var2)? #var2 + ' is even number'  : #var2 + ' is not even number'";

		queryResultString = spelExpressionParser.parseExpression(expression).getValue(context, String.class);
		System.out.println(queryResultString);

	}

}
