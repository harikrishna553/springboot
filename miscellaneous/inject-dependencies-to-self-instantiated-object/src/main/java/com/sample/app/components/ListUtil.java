package com.sample.app.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListUtil {

	@Autowired
	private StringUtil stringUtil;

	public List<String> toUpper(List<String> strs) {
		return stringUtil.toUpper(strs);

	}
}
