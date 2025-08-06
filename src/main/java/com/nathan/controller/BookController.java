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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// Details book
	@GetMapping("/{id}")
	public String getMethodName(@PathVariable long id, Model model) {

		Book book = bookService.getBook(id);

		System.out.println(book);

		model.addAttribute("messenger", book);

		return "bookDetails";
	}

	// Delete book
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		
		bookService.deleteBook(id);
		
		return "redirect:/books";

	}

	
	// Edit book
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable long id,Model model) {
		// récup du livre ou du livre par défaut si il n'y a pas livre trouvé
		
		Book book= bookService.findBookById(id);
		
		model.addAttribute("bookToUpdate", book);
		
		// retuiurn le formuaire de modif a qui j'envoie le livre par mopdel bookToUpdate
		return "editForm";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * // Add book // This method returns a form to add a new book
	 * 
	 * @GetMapping("/add") public String getAddForm(Model model) { // Create a new
	 * book object to bind to the form model.addAttribute("book", new Book());
	 * return "bookAdd"; }
	 */

	/*
	 * @DeleteMapping("/delete/{id}") public ResponseEntity<String>
	 * deleteBook(@PathVariable Long id) { bookService.deleteBook(id); return
	 * ResponseEntity.ok("Book deleted successfully"); }
	 */
	// Edit book
	//

	/*
	 * @PostMapping public String addBook(@ModelAttribute Book book) {
	 * 
	 * bookService.addBook(book);
	 * 
	 * return "redirect:/books"; }
	 */
}
