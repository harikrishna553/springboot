package com.sample.app.collections;

import java.util.List;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InlineListDemo {
	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		List<Integer> primes = spelExpressionParser.parseExpression("{2, 3, 5, 7}").getValue(List.class);
		List<List<Integer>> evenAndOdds = spelExpressionParser.parseExpression("{{2, 4}, {1, 3}}").getValue(List.class);

		Integer secondPrime = spelExpressionParser.parseExpression("{2, 3, 5, 7}[1]").getValue(Integer.class);
		Integer secondEven = spelExpressionParser.parseExpression("{{2, 4}, {1, 3}}[0][1]").getValue(Integer.class);

		System.out.println("primes : " + primes);
		System.out.println("evenAndOdds : " + evenAndOdds);
		System.out.println("secondPrime : " + secondPrime);
		System.out.println("secondEven : " + secondEven);

	}
}
