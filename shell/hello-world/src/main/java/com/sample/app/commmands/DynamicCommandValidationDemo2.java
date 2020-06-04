package com.sample.app.commmands;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellCommandGroup("Dynamic Command Validation")
@ShellComponent(value = "Connect to Oracle Database")
public class DynamicCommandValidationDemo2 {

	private boolean connected;

	@ShellMethod(value = "Connect to Oracle Database", key = "connect-to-oracle", prefix = "-")
	public void connectToDb(String userName, String password) {
		connected = true;
	}

	@ShellMethodAvailability("availabilityCheck")
	@ShellMethod(value = "Print all records of table from Oracle DB", key = "print-table-records-from-oracle", prefix = "-")
	public void printAllRecordsOfTable(String tableName) {
		System.out.println("Printing Data.......");
	}

	public Availability availabilityCheck() {
		return connected ? Availability.available() : Availability.unavailable("you are not connected");
	}
}
