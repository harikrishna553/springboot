package com.sample.app.util;

public class ArithmeticUtil {

	public static long sum(int... a) {
		if (a == null) {
			return 0;
		}
		
		long sum = 0;
		
		for(int i : a) {
			sum += i;
		}
		
		return sum;
		
	}
}
