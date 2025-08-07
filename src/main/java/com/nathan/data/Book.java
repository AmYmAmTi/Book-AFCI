package com.nathan.data;

import java.io.Serializable;

import jakarta.persistence.*;

/**
 * Entity class representing a Book.
 * This class is mapped to a table in the database using JPA annotations.
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    // ================================
    // Fields / Properties
    // ================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "nbPage", nullable = false)
    private int nbPage;

    // ================================
    // Constructors
    // ================================

    // Default constructor (required by JPA)
    public Book() {
        // no-args constructor
    }

    // Full constructor
    public Book(String title, int nbPage) {
        this.title = title;
        this.nbPage = nbPage;
    }

    // Partial constructor (used for fallback or simplified creation)
    public Book(String title) {
        this.title = title;
        this.nbPage = 0; // default value
    }

    // ================================
    // Getters
    // ================================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNbPage() {
        return nbPage;
    }

    // ================================
    // Setters
    // ================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    // ================================
    // toString()
    // ================================

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", nbPage=" + nbPage + "]";
    }

    /*
     * 🔔 Notes:
     * - `Serializable` is implemented for best practice in JavaBeans used in persistence.
     * - `@Entity` marks this as a JPA-managed entity.
     * - All fields are private and accessed via getters/setters.
     * - You can extend this class in the future to add author, category, price, etc.
     */
}
