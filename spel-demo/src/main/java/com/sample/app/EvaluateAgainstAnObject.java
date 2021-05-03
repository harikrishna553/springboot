package com.sample.app;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.sample.app.model.Employee;

public class EvaluateAgainstAnObject {
	public static void main(String[] args) {
		Employee emp1 = new Employee(1, "Krishna", "Gurram");

		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		String expressionString = "id";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		Integer id = exp.getValue(emp1, Integer.class);
		System.out.println("id :" + id);

		expressionString = "firstName";
		exp = spelExpressionParser.parseExpression(expressionString);
		String firstName = exp.getValue(emp1, String.class);
		System.out.println("\nfirstName :" + firstName);

		expressionString = "lastName";
		exp = spelExpressionParser.parseExpression(expressionString);
		String lastName = exp.getValue(emp1, String.class);
		System.out.println("\nlastName :" + lastName);

		expressionString = "lastName.length";
		exp = spelExpressionParser.parseExpression(expressionString);
		Integer lastNameLength = exp.getValue(emp1, Integer.class);
		System.out.println("\nlastNameLength :" + lastNameLength);

		expressionString = "lastName.toUpperCase";
		exp = spelExpressionParser.parseExpression(expressionString);
		String lastNameInUpperCase = exp.getValue(emp1, String.class);
		System.out.println("\nlastNameInUpperCase :" + lastNameInUpperCase);

	}
}
