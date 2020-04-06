package com.sample.app.readers;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyItemReader implements ItemReader<String> {

	private Iterator<String> items;

	public MyItemReader(Iterator<String> items) {
		this.items = items;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (items.hasNext()) {
			String item = items.next();
			System.out.println("Reading item : " + item);

			return item;
		}
		return null;
	}

}
