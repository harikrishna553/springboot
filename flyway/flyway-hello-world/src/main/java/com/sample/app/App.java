package com.sample.app;

import org.flywaydb.core.Flyway;

public class App {
	public static void main(String[] args) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/abc_org", "root", "tiger").load();

        // Start the migration
        flyway.migrate();
    }
}
