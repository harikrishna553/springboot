package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent(value = "Customize Named Parameter keys using @ShellOption")
public class CustomizeNamedParamKeys {
	@ShellMethod(value = "Commands to print Employee Details", key = "print-employee", prefix = "-")
	public String printEmployeeDetails(String name, @ShellOption("emp-age") int age,
			@ShellOption("--emp-city") String city) {

		StringBuilder builder = new StringBuilder();

		builder.append("Hello ").append(name).append("!!!!").append(". You are ").append(age)
				.append(" years old and you are from ").append(city);

		return builder.toString();
	}
}
