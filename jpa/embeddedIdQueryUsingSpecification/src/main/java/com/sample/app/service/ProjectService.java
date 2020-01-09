package com.sample.app.service;

import java.util.List;

import com.sample.app.entity.Project;
import com.sample.app.model.SearchQuery;

public interface ProjectService {

	 public List<Project> findAll(SearchQuery searchQuery);
}
