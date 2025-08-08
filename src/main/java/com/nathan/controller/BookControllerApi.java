package com.nathan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.data.Book;
import com.nathan.service.BookService;

// http://localhost:31001/api/books/3
// http://localhost:31001/api/books/name/coran

@RestController
@RequestMapping("/api/books")
public class BookControllerApi {

	private final BookService bookService;

	public BookControllerApi(BookService bookService) {
		super();
		this.bookService = bookService;

	}

	@GetMapping

	public List<Book> getAllBooks() {

		List<Book> books = bookService.getAllBooks();

		return books;
	}

	@GetMapping("/{id}")
	public Book bookDetails(@PathVariable Long id) {
		return bookService.getBookById(id);

	}

	@GetMapping("/name/{name}")
	public Book bookDetails(@PathVariable("name") String name) {
		System.out.println(name);
		return new Book("coran", 100);

	}

	@DeleteMapping("/{id}")
	public List<Book> removeBooks(@PathVariable Long id) {
		System.out.println(id);
		bookService.removeBooks(id);
		return bookService.getAllBooks();

	}

	@PostMapping
	public List<Book> addBooks(@RequestBody Book book) {
		bookService.addBook(book);
		return bookService.getAllBooks();

	}

	@PutMapping("/{id}")
	public List<Book> EditBooks(@PathVariable Long id) {
		System.out.println(id);
		bookService.removeBooks(id);
		return bookService.getAllBooks();

	}

}
