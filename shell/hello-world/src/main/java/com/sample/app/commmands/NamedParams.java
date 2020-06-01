package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Named Parameters Demo")
public class NamedParams {
	@ShellMethod(value = "Commands to print User Details", key = "get-user")
	public String printUserDetails(String name, int age, String city) {

		StringBuilder builder = new StringBuilder();

		builder.append("Hello ").append(name).append("!!!!").append(". You are ").append(age)
				.append(" years old and you are from ").append(city);

		return builder.toString();
	}
}
