package com.nathan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nathan.data.Book;
import com.nathan.data.BookRepository;
import com.nathan.service.BookService;

@Controller
@RequestMapping(path = "/books")
public class BookController {

	private final BookRepository bookRepository;

	private final BookControllerApi bookControllerApi;

	// Attributes-------------------------------------
	//
	// ------------------------------------------------

	private final BookService bookService;

	public BookController(BookService bookService, BookControllerApi bookControllerApi, BookRepository bookRepository) {
		super();
		this.bookService = bookService;
		this.bookControllerApi = bookControllerApi;
		this.bookRepository = bookRepository;
	}

	@GetMapping
	public String getAllBooks(Model model) {

		List<Book> books = bookService.getAllBooks();

		System.out.println(books);

		model.addAttribute("messenger", books);

		return "books";
	}

	@GetMapping("/{id}")

	public String getMethodName(@PathVariable long id, Model model) {

		Book book = bookService.getBook(id);

		System.out.println(book);

		model.addAttribute("messenger", book);

		return "bookDetails";
	}

	// Add book
	// This method returns a form to add a new book
	@GetMapping("/add")
	public String getAddForm(Model model) {
		// Create a new book object to bind to the form
		model.addAttribute("book", new Book());
		return "bookAdd";
	}

	// Delete book
	@DeleteMapping("/delete/{id}")
	public void deleteBook(@PathVariable Long id) {

		bookRepository.deleteById(id);

	}

	@PostMapping
	public String addBook(@ModelAttribute Book book) {

		bookService.addBook(book);

		return "redirect:/books";
	}

}
