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
    // Dependency Injection
    // ================================
    private final BookRepository bookRepository;

    // Constructor-based injection (recommended for immutability)
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ================================
    // 1. Get All Books
    // ================================
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    // ================================
    // 2. Get Book by ID (fallback if not found)
    // Returns a dummy Book object if the ID is invalid
    // ================================
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    
    
    
    // Alias method for compatibility
    public Book getBook(long id) {
        return getBook(id);
    }

    // ================================
    // 3. Add New Book
    // ================================
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    // ================================
    // 4. Update Book
    // ================================
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    // ================================
    // 5. Delete Book by ID
    // ================================
    public void removeBooks(Long id) {
        bookRepository.deleteById(id);
    }

    // ================================
    // Notes:
    // - getBookById ensures no null is returned to avoid template errors.
    // - updateBook will insert if the book ID does not exist.
    // - Always use constructor injection for better testability.
    // ================================
}
