package com.sample.app.readers;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyStatefulItemReader implements ItemStreamReader<String> {

	private List<String> items;
	private int currentIndex;
	private boolean firstRun;

	public MyStatefulItemReader(List<String> items) {
		this.items = items;
		currentIndex = 0;
		firstRun = true;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("In open method");
		if (executionContext.containsKey("currentItemIndex")) {
			this.currentIndex = executionContext.getInt("currentItemIndex");
			this.firstRun = false;
		} else {
			this.currentIndex = 0;
			executionContext.put("currentItemIndex", this.currentIndex);
		}

	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("In update method");
		executionContext.put("currentItemIndex", this.currentIndex);
	}

	@Override
	public void close() throws ItemStreamException {
		System.out.println("In close method");

	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("In read method");
		String item = null;

		if (currentIndex == 9 && firstRun) {
			throw new RuntimeException("Error Occuured while processing the data");
		}

		if (currentIndex < items.size()) {
			item = items.get(currentIndex);
			currentIndex++;
		}

		return item;
	}

}
