package com.sample.app.commmands;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent(value = "Connect to Database")
public class DynamicCommandValidationDemo1 {

	private boolean connected;

	@ShellMethod(value = "Connect to Database", key = "connect", prefix = "-")
	public void connectToDb(String userName, String password) {
		connected = true;
	}

	@ShellMethod(value = "Print all records of table", key = "print-table-records", prefix = "-")
	public void printAllRecordsOfTable(String tableName) {
		System.out.println("Printing Data.......");
	}

	public Availability printAllRecordsOfTableAvailability() {
		return connected ? Availability.available() : Availability.unavailable("you are not connected");
	}
}
