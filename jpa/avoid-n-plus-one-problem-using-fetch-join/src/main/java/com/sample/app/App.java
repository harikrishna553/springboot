package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.entity.Author;
import com.sample.app.entity.Book;
import com.sample.app.repository.AuthorRepository;
import com.sample.app.service.AuthorService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class App {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorService authorService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	@Transactional
	public void demo() {
		Author hari = new Author("Hari");
		hari.addBook(new Book("Hari Book 1"));
		hari.addBook(new Book("Hari Book 2"));

		Author ram = new Author("Ram");
		ram.addBook(new Book("Ram Book 1"));
		ram.addBook(new Book("Ram Book 2"));

		Author david = new Author("David");
		david.addBook(new Book("David Book 1"));
		david.addBook(new Book("David Book 2"));

		Author chandu = new Author("Chandu");
		chandu.addBook(new Book("David Book 1"));

		Author raghu = new Author("Raghu");
		raghu.addBook(new Book("Raghu Book 1"));

		authorRepository.save(hari);
		authorRepository.save(ram);
		authorRepository.save(david);
		authorRepository.save(chandu);
		authorRepository.save(raghu);

		authorService.printAll();
	}
}
