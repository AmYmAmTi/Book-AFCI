package com.nathan.data;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	private long id;

	
    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

	@Column(name = "adress", nullable = false, length = 300)
	private String address;

	// ================================
	// Relationships
	// ================================

	// One Author can have many Books
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)

	private List<Book> books;


	

	// ================================
	// Constructors
	// ================================

	public Author() {
		super();
	}

	public Author(String fullName, String address) {
		super();
		this.fullName = fullName;
		this.address = address;
	}

	public Author(String fullName) {
		super();
		this.fullName = fullName;
		this.address = null;
	}

	// ================================
	// Getters
	// ================================

	public long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAddress() {
		return address;
	}

	// ================================
	// Setters
	// ================================

	public void setId(long id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// ================================
	// toString()
	// ================================
	@Override
	public String toString() {
		return "Author [id=" + id + ", fullName=" + fullName + ", address=" + address + "]";
	}

}
