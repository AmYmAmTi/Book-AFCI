package com.nathan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nathan.data.Book;
import com.nathan.data.BookRepository;

@Service
public class BookService {
	// This class is responsible for business logic related to books

	private final BookRepository bookRepository;

	// alt + shift + S -> Generate constructor using fields

	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	// Methods to handle book operations

	public List<Book> getAllBooks() {
		// get all books

		List<Book> books = new ArrayList<>();

		Iterable<Book> liste = bookRepository.findAll();

		liste.forEach(book -> books.add(book));

		return books;

	}

	public Book getBook(long id) {

		Optional<Book> book = bookRepository.findById(id);

		return book.orElse(null);

	}

	public void addBook(Book b) {
		bookRepository.save(b);
		
	}

}
