package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Customize Named Parameters Prefix")
public class CustomizeNamedParamKeysPrefix {
	@ShellMethod(value = "Commands to print Student Details", key = "print-student", prefix="-")
	public String printStudentDetails(String name, int age, String city) {

		StringBuilder builder = new StringBuilder();

		builder.append("Hello ").append(name).append("!!!!").append(". You are ").append(age)
				.append(" years old and you are from ").append(city);

		return builder.toString();
	}
}
