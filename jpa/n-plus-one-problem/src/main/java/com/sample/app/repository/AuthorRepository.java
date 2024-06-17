package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
