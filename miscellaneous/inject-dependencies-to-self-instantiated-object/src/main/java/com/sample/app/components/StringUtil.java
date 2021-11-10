package com.sample.app.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public List<String> toUpper(List<String> strs) {
		if (strs == null || strs.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<String> uppercaseStrings = new ArrayList<>();
		for (String str : strs) {
			uppercaseStrings.add(str.toUpperCase());
		}
		return uppercaseStrings;

	}

}
