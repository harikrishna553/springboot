package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	@EntityGraph(attributePaths = {"books"})
	List<Author> findAll();

}
