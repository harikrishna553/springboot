package com.sample.app.commmands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Handle Boolean Values")
public class BooleanTypeDemo {

	@ShellMethod(value = "Shutdown the system", key = "shutdown", prefix = "-")
	public void shutdown(boolean force) {
		if(force) {
			System.out.println("Shuting Down the System Forcefully");
			return;
		}
		
		System.out.println("Shuting Down the System Gracefully");
	}
}
