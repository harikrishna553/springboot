package com.sample.app.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.Project;

public interface ProjectRepository extends ArangoRepository<Project, String> {

}
