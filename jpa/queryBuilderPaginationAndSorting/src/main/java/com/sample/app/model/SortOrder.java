package com.sample.app.model;

import java.util.List;

public class SortOrder {

	private List<String> ascendingOrder;

	private List<String> descendingOrder;

	public List<String> getAscendingOrder() {
		return ascendingOrder;
	}

	public void setAscendingOrder(List<String> ascendingOrder) {
		this.ascendingOrder = ascendingOrder;
	}

	public List<String> getDescendingOrder() {
		return descendingOrder;
	}

	public void setDescendingOrder(List<String> descendingOrder) {
		this.descendingOrder = descendingOrder;
	}

}
