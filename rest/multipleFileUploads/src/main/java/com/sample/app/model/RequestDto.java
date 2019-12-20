package com.sample.app.model;

import org.springframework.web.multipart.MultipartFile;

public class RequestDto {
	private String description;
	private MultipartFile[] documents;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile[] getDocuments() {
		return documents;
	}

	public void setDocuments(MultipartFile[] document) {
		this.documents = document;
	}

}
