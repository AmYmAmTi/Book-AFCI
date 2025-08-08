package com.nathan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nathan.data.Book;
import com.nathan.data.BookRepository;

@Service
public class BookService {

    // ================================
    // Dependencies
    // ================================
    private final BookRepository bookRepository;

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ================================
    // 1. Get All Books
    // ================================
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Iterable<Book> result = bookRepository.findAll();
        result.forEach(books::add); // Java 8 method reference
        return books;
    }

    // ================================
    // 2. Get Book by ID (returns null if not found)
    // ================================
    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }

    // ================================
    // 3. Add New Book
    // ================================
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    // ================================
    // 4. Delete Book by ID
    // ================================
    public void removeBooks(Long id) {
        bookRepository.deleteById(id);
    }

    // ================================
    // 5. Find Book for Edit (fallback if not found)
    // Used when editing to pre-fill the form
    // ================================
    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(new Book("Book not found", 0));
    }

    // ================================
    // 6. Update Book (used for saving edited book)
    // ================================
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    // ================================
    // ❌ UNUSED - Placeholder for future use
    // ================================
    public void editBook(Long id) {
        // Method not used - consider removing or implementing
        // Example: retrieve + update logic could go here
    }

    /*
     * 🔔 Notes:
     * - The service handles core logic and acts as a bridge between controller and repository.
     * - `findBookById` provides a fallback book if ID is invalid (for edit form).
     * - `updateBook` uses `save()` which updates if ID exists, or inserts if not.
     */
}
