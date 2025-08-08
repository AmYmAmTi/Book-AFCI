package com.nathan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nathan.data.Book;
import com.nathan.data.BookRepository;
import com.nathan.data.EditorRepository;
import com.nathan.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    // ============================
    // Dependencies
    // ============================
    private final BookService bookService;
    private final EditorRepository editorRepository;
    //private final BookControllerApi bookControllerApi;
    //private final BookRepository bookRepository;

    // Constructor Injection
    

    // ============================
    // 1. List All Books
    // ============================
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("messenger", books);
        return "books"; // View: books.html
    }

    public BookController(BookService bookService, EditorRepository editorRepository) {
		super();
		this.bookService = bookService;
		this.editorRepository = editorRepository;
	}

	// ============================
    // 2. View Book Details
    // ============================
    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("messenger", book);
        return "bookDetails"; // View: bookDetails.html
    }

    // ============================
    // 3. Show Form to Add Book
    // ============================
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("editors", editorRepository.findAll());
        System.out.println(new Book().getEditor()); // Should not throw an error

        return "bookAddForm"; // View: bookAdd.html
    }

    // ============================
    // 4. Process Add Book Form
    // ============================
    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // ============================
    // 5. Show Form to Edit Book
    // ============================
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("bookToUpdate", book);
        return "bookEditForm"; // View: bookEdit.html
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
     * 🔔 Notes:
     * - The controller is structured by action type (List, Details, Add, Edit, Delete).
     * - View names must match HTML templates inside `src/main/resources/templates/`.
     * - `th:object` in forms must match the model attribute names: "book" or "bookToUpdate".
     * - Using GET for deletion simplifies links in HTML, but for real REST APIs use DELETE.
     */
}
