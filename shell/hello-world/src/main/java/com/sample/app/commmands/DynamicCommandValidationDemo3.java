package com.sample.app.commmands;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent(value = "Connect to DB2 Database")
public class DynamicCommandValidationDemo3 {

	private boolean connected;

	@ShellMethod(value = "Connect to Db2 Database", key = "connect-to-db2", prefix = "-")
	public void connectToDb(String userName, String password) {
		connected = true;
	}

	@ShellMethod(value = "Print all records of table from DB2", key = "print-table-records-from-db2", prefix = "-")
	public void printAllRecordsOfTable(String tableName) {
		System.out.println("Printing Data.......");
	}
	
	@ShellMethod(value = "Download all records of table from DB2", key = "download-table-records-from-db2", prefix = "-")
	public void download(String tableName) {
		System.out.println("Downloading Data.......");
	}

	@ShellMethodAvailability({"download-table-records-from-db2", "print-table-records-from-db2"})
	public Availability availabilityCheck() {
		return connected ? Availability.available() : Availability.unavailable("you are not connected");
	}
}
