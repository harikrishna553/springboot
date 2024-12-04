package com.sample.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostRequestDto {

	private String title;

	private String content;

	private List<String> tags;

}
