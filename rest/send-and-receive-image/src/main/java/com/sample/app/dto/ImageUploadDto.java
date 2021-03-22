package com.sample.app.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDto {
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

	public void setDocuments(MultipartFile[] documents) {
		this.documents = documents;
	}

}
