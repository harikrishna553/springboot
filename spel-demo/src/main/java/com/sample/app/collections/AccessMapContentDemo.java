package com.sample.app.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Person;

public class AccessMapContentDemo {
	public static void main(String args[]) {
		Map<String, Object> map = new HashMap<>();
		map.put("height", 6.2);
		map.put("weight", 75.23);

		Person person = new Person(1, "Krishna", Collections.EMPTY_LIST, map);

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "otherProperties['height']";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		Double personHeight = exp.getValue(person, Double.class);

		expressionString = "otherProperties['weight']";
		exp = spelExpressionParser.parseExpression(expressionString);
		Double personWeight = exp.getValue(person, Double.class);

		System.out.println("personHeight : " + personHeight);
		System.out.println("personWeight : " + personWeight);

	}
}
