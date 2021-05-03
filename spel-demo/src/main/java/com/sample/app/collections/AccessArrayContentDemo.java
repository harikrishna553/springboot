package com.sample.app.collections;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Student;

public class AccessArrayContentDemo {
	public static void main(String args[]) {
		String[] hobbies = { "Trekking", "Singing", "Blogging" };
		Student stud1 = new Student(1, "Krishna", hobbies);

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "hobbies.length";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		int noOfHobbies = exp.getValue(stud1, Integer.class);

		System.out.println("Given student has " + noOfHobbies + " hobbies");

		for (int i = 0; i < noOfHobbies; i++) {
			expressionString = "hobbies[" + i + "]";
			exp = spelExpressionParser.parseExpression(expressionString);
			String hobby = exp.getValue(stud1, String.class);
			System.out.println(hobby);
		}

	}

}
