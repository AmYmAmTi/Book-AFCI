package com.nathan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nathan.data.Book;
import com.nathan.data.BookRepository;
import com.nathan.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    // ============================
    // Dependencies
    // ============================
    private final BookService bookService;
    private final BookControllerApi bookControllerApi;
    private final BookRepository bookRepository;

    // Constructor Injection
    public BookController(BookService bookService, BookControllerApi bookControllerApi, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookControllerApi = bookControllerApi;
        this.bookRepository = bookRepository;
    }

    // ============================
    // 1. List All Books
    // ============================
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("messenger", books);
        return "books"; // View: books.html
    }

    // ============================
    // 2. View Book Details
    // ============================
    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("messenger", book);
        return "bookDetails"; // View: bookDetails.html
    }

    // ============================
    // 3. Show Form to Add Book
    // ============================
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookAdd"; // View: bookAdd.html
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
        return "editForm"; // View: editForm.html
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
        bookService.deleteBook(id);
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
