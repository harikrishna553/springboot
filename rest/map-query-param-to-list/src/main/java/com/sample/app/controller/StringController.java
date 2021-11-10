package com.sample.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {
	@RequestMapping("/to-upper")
	public List<String> home(@RequestParam(name = "str") List<String> strs) {
		if (strs == null || strs.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List<String> upperCaseStrings = new ArrayList<>();

		for (String str : strs) {
			upperCaseStrings.add(str.toUpperCase());
		}

		return upperCaseStrings;
	}
}