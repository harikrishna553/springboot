package com.sample.app.commmands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.sample.app.customizations.ShellOutputHelper;

@ShellComponent
public class CustomizeColorCommand {
	@Autowired
	ShellOutputHelper shellHelper;

	@ShellMethod("Displays greeting message to the user whose name is supplied")
	public String beautifyMe(@ShellOption({ "-N", "--name" }) String name) {
		String output = shellHelper.getSuccessMessage(String.format("Hello %s", name));
		return output.concat(", How are you?");
	}
}
