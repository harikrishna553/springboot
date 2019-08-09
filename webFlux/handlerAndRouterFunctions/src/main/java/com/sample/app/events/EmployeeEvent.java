package com.sample.app.events;

public class EmployeeEvent {
	private Long eventId;
	private String eventType;

	public EmployeeEvent(Long eventId, String eventType) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeEvent [eventId=").append(eventId).append(", eventType=").append(eventType).append("]");
		return builder.toString();
	}

}
