package com.nathan.data;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "nbPage", nullable = false)
    private int nbPage;

    // Constructors
    public Book() {
        super();
    }

    public Book(String title, int nbPage) {
        this.title = title;
        this.nbPage = nbPage;
    }

    public Book(String title) {
        this.title = title;
    }

    // ✅ Getters 
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNbPage() {
        return nbPage;
    }

    //  Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", nbPage=" + nbPage + "]";
    }
}
