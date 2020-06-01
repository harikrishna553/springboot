package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Commands to perform Arithmetic Operations by customizing key name")
public class ArithmeticCommandCustomizeCommandName {
	
	@ShellMethod(value = "Add two integers together.", key="sum")
	public int add(int a, int b) {
		return a + b;
	}
}
