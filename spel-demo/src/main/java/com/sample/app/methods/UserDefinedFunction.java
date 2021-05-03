package com.sample.app.methods;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import com.sample.app.util.ArithmeticUtil;

public class UserDefinedFunction {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		ExpressionParser spelExpressionParser = new SpelExpressionParser();

		EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
		context.setVariable("sumCalc", ArithmeticUtil.class.getDeclaredMethod("sum", int[].class));

		Long result = spelExpressionParser.parseExpression("#sumCalc(1, 2, 3, 4, 5)").getValue(context, Long.class);

		System.out.println("result : " + result);

	}

}
