package com.sample.app.collections;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ArrayCreateDemo {
	public static void main(String args[]) {
		ExpressionParser parser = new SpelExpressionParser();

		int[] singleDimensionalEmptyArray = (int[]) parser.parseExpression("new int[4]").getValue(int[].class);

		int[] singleDimensionalArrayWithElements = (int[]) parser.parseExpression("new int[]{1,2,3}")
				.getValue(int[].class);

		int[][] multiDimenstionalEmptyArray = (int[][]) parser.parseExpression("new int[2][3]").getValue(int[][].class);

		// Using an initializer to build a multi-dimensional array is not currently
		// supported
		/*
		 * int[][] multiDimenstionalArrayWithElements = (int[][])
		 * parser.parseExpression("new int[2][3] {{1, 2, 3}, {4, 5, 6}}")
		 * .getValue(int[][].class);
		 */

		System.out.println("singleDimensionalEmptyArray size: " + singleDimensionalEmptyArray.length);

		System.out.println("\nsingleDimensionalArrayWithElements size : " + singleDimensionalArrayWithElements.length);
		System.out.println("Elements in singleDimensionalArrayWithElements are");
		for (int i = 0; i < singleDimensionalArrayWithElements.length; i++) {
			System.out.println(singleDimensionalArrayWithElements[i]);
		}

		System.out.println("\nmultiDimenstionalEmptyArray rows : " + multiDimenstionalEmptyArray.length);
		System.out.println("multiDimenstionalEmptyArray columns : " + multiDimenstionalEmptyArray[0].length);

	}
}
