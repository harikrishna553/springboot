package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Multiple names to the same commands")
public class MultipleAliases {

	@ShellMethod(value = "Commands to welcome user", key = { "welcome", "wish", "say-hello" })
	public String sayHello(String name) {
		return "Good Morning " + name + "!!!!!";
	}
}
