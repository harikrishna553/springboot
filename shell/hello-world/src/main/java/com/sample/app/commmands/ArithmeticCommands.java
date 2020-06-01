package com.sample.app.commmands;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class ArithmeticCommands {

	@ShellMethod("Add two integers together.")
	public int add(int a, int b) {
		return a + b;
	}

	@ShellMethod("Subtract two integers together.")
	public int sub(int a, int b) {
		return a - b;
	}

	@ShellMethod("Multiply two integers together.")
	public int mul(int a, int b) {
		return a * b;
	}

	@ShellMethod("Devide two integers together.")
	public int div(int a, int b) {
		return a / b;
	}
}