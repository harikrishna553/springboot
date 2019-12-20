package com.sample.app.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseDto {
	private String description;
	private Map<String, String> fileContent = new HashMap<> ();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getFileContent() {
		return fileContent;
	}

	public void setFileContent(Map<String, String> fileContent) {
		this.fileContent = fileContent;
	}

}
