package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sample.app.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>, CrudRepository<Project, Integer>  {

}
