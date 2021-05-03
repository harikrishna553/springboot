package com.sample.app.collections;

import java.util.Map;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InlineMapDemo {
	public static void main(String args[]) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String mapData = "{'name' : {'firstName' : 'Krishna', 'lastName' : 'Gurram'}, 'age' : 31}";
		Map<String, Object> emp = spelExpressionParser.parseExpression(mapData).getValue(Map.class);
		System.out.println("emp : " + emp);
		
		String firstNameExpression = "{'name' : {'firstName' : 'Krishna', 'lastName' : 'Gurram'}, 'age' : 31}['name']['firstName']";
		String firstName = spelExpressionParser.parseExpression(firstNameExpression).getValue(String.class);
		System.out.println("firstName : " + firstName);

	}
}
