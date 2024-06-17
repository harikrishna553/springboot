package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
