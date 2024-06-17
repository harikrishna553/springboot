package com.sample.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.entity.Author;
import com.sample.app.entity.Book;
import com.sample.app.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Transactional
	public void printAll() {
		List<Author> authors = authorRepository.findAll();

		for (Author author : authors) {
			System.out.println("author : " + author);
			for (Book book : author.getBooks()) {
				System.out.println("\t" + book);
			}
		}
	}
}
