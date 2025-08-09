package com.nathan.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nathan.data.AuthorRepository;
import com.nathan.data.Book;
import com.nathan.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;
	private final AuthorRepository euthorRepository;
	// Constructor injection

	public BookController(BookService bookService, AuthorRepository euthorRepository) {
		super();
		this.bookService = bookService;
		this.euthorRepository = euthorRepository;
	}

	// ========================================
	// 1. List All Books
	// ========================================
	@GetMapping
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "bookList";
	}

	// ========================================
	// 2. View Book Details
	// ========================================
	@GetMapping("/{id}")
	public String bookDetails(@PathVariable Long id, Model model) {
		Optional<Book> bookOpt = bookService.findById(id);
		if (bookOpt.isPresent()) {
			model.addAttribute("book", bookOpt.get());
			return "bookDetails";
		} else {
			return "redirect:/books"; // أو صفحة خطأ
		}
	}

	// ============================
	// 3. Show Form to Add Book
	// ============================
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("authors", euthorRepository.findAll());
		System.out.println(new Book().getAuthor()); // Should not throw an error
		return "bookAdd";
	}

	// ============================
	// 4. Save New Book
	// ============================

	@PostMapping("/add")
	public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("authors", euthorRepository.findAll());
			return "bookAdd";
		}
		bookService.addBook(book);
		return "redirect:/books";
	}

	
    // ============================
    // 5. Show Form to Edit Book
    // ============================
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable long id, Model model) {
	    Book book = bookService.findById(id)
	                           .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
	    
	    model.addAttribute("bookToUpdate", book);
	    model.addAttribute("authors", euthorRepository.findAll());
	    
	  //  System.out.println("Authors count: " + authors.size());

	    return "bookEdit"; // View: bookEdit.html
	}

		
    // ============================
    // 6. Process Edit Book Form
    // ============================
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("bookToUpdate") Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
	
	
	// ============================
	// 7. Delete Book
	// ============================
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.removeBooks(id);
		return "redirect:/books";
	}

	/*
	 * 🔔 Notes: - The controller is structured by action type (List, Details, Add,
	 * Edit, Delete). - View names must match HTML templates inside
	 * `src/main/resources/templates/`. - `th:object` in forms must match the model
	 * attribute names: "book" or "bookToUpdate". - Using GET for deletion
	 * simplifies links in HTML, but for real REST APIs use DELETE.
	 */
}
