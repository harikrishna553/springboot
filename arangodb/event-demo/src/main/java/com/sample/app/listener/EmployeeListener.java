package com.sample.app.listener;

import org.springframework.stereotype.Component;

import com.arangodb.springframework.core.mapping.event.AbstractArangoEventListener;
import com.arangodb.springframework.core.mapping.event.AfterDeleteEvent;
import com.arangodb.springframework.core.mapping.event.AfterSaveEvent;
import com.arangodb.springframework.core.mapping.event.BeforeDeleteEvent;
import com.arangodb.springframework.core.mapping.event.BeforeSaveEvent;
import com.sample.app.entity.Employee;

@Component
public class EmployeeListener extends AbstractArangoEventListener<Employee> {
	public void onBeforeSave(final BeforeSaveEvent<Employee> event) {
		System.out.println("Before saving : " + event.getSource());
	}

	public void onAfterSave(final AfterSaveEvent<Employee> event) {
		System.out.println("After saving : " + event.getSource());
	}

	public void onBeforeDelete(final BeforeDeleteEvent<Employee> event) {
		System.out.println("Before deleting : " + event.getSource());
	}

	public void onAfterDelete(final AfterDeleteEvent<Employee> event) {
		System.out.println("After deleting : " + event.getSource());
	}

}
