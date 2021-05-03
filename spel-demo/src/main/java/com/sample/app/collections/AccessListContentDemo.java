package com.sample.app.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Person;

public class AccessListContentDemo {
	public static void main(String args[]) {
		List<String> hobbies = Arrays.asList("Trekking", "Singing", "Blogging");
		Person person = new Person(1, "Krishna", hobbies, Collections.EMPTY_MAP);

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "hobbies.size";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		int noOfHobbies = exp.getValue(person, Integer.class);

		System.out.println("Given person has " + noOfHobbies + " hobbies");
		String hobby = null;
		for (int i = 0; i < noOfHobbies; i++) {
			expressionString = "hobbies[" + i + "]";
			exp = spelExpressionParser.parseExpression(expressionString);
			hobby = exp.getValue(person, String.class);
			System.out.println(hobby);
		}

	}
}
