package com.sample.app.commmands;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellCommandGroup("Miscellaneous")
@ShellComponent(value = "Specify Default Values using using @ShellOption")
public class DefaultValues {

	@ShellMethod(value = "Commands to greet user", key = "greet-user", prefix = "-")
	public String greetUser(@ShellOption(defaultValue = "User") String name) {

		StringBuilder builder = new StringBuilder();

		builder.append("Hello ").append(name).append("!!!!");

		return builder.toString();
	}
}
