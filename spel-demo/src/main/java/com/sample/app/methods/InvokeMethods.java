package com.sample.app.methods;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class InvokeMethods {
	public static void main(String[] args) {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		// Length of a string
		String expressionString = "'Hello World'.length";
		Expression exp = spelExpressionParser.parseExpression(expressionString);
		Integer stringLength = exp.getValue(Integer.class);
		System.out.println("String length " + stringLength);

		// Concatenate two strings
		expressionString = "'Hello '.concat('World')";
		exp = spelExpressionParser.parseExpression(expressionString);
		String resultStr = exp.getValue(String.class);
		System.out.println("\nConcat result : " + resultStr);

		// Get byte array from string
		expressionString = "'Hello World'.bytes";
		exp = spelExpressionParser.parseExpression(expressionString);
		byte[] byteArray = exp.getValue(byte[].class);
		System.out.println("\nByte Array: ");
		for (byte byteTemp : byteArray) {
			System.out.print(byteTemp + ",");
		}

		// Access nested properties
		expressionString = "'   Hello World   '.trim.length";
		exp = spelExpressionParser.parseExpression(expressionString);
		stringLength = exp.getValue(Integer.class);
		System.out.println("\n\nString length after trimming " + stringLength);

	}
}
