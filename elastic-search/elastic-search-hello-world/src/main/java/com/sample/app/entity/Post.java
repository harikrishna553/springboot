package com.sample.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "posts")
@Data
public class Post {

	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String title;

	@Field(type = FieldType.Text)
	private String content;

	@Field(type = FieldType.Keyword)
	private List<String> tags;

}
