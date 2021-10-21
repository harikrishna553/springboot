package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
