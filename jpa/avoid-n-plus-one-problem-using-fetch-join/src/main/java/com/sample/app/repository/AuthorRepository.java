package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	@Query("SELECT a FROM Author a JOIN FETCH a.books")
	List<Author> findAll();

}
