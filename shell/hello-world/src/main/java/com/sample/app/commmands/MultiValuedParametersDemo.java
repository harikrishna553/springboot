package com.sample.app.commmands;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellCommandGroup("Miscellaneous")
@ShellComponent(value = "Multi Valued Parameters Demo")
public class MultiValuedParametersDemo {

	@ShellMethod(value = "Commands to Calculate Square of given numbers", key = "square", prefix = "-")
	public String SquarelOfNumbers(@ShellOption(arity=5)int[] input, String message) {

		StringBuilder builder = new StringBuilder();

		for (int i : input) {
			int result = i * i;
				
			builder.append(String.format(message, i, result)).append("\n");
		}

		return builder.toString();
	}
}
