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
public class Editor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editor")
	private long id;

	
    @Column(name = "name_editor", nullable = false, length = 100)
    private String nameEditor;

	@Column(name = "adress_editor", nullable = false, length = 300)
	private String addressEditor;

	// ================================
	// Relationships
	// ================================

	// One Editor can have many Books
	@OneToMany(mappedBy = "editor", cascade = CascadeType.ALL)

	private List<Book> books;

	// ================================
	// Constructors
	// ================================

	public Editor() {
		super();
	}

	public Editor(String nameEditor, String addressEditor) {
		super();
		this.nameEditor = nameEditor;
		this.addressEditor = addressEditor;
	}

	public Editor(String nameEditor) {
		super();
		this.nameEditor = nameEditor;
		this.addressEditor = null;
	}

	// ================================
	// Getters
	// ================================

	public long getId() {
		return id;
	}

	public String getNameEditor() {
		return nameEditor;
	}

	public String getAddressEditor() {
		return addressEditor;
	}

	// ================================
	// Setters
	// ================================

	public void setId(long id) {
		this.id = id;
	}

	public void setNameEditor(String nameEditor) {
		this.nameEditor = nameEditor;
	}

	public void setAddressEditor(String addressEditor) {
		this.addressEditor = addressEditor;
	}

	// ================================
	// toString()
	// ================================
	@Override
	public String toString() {
		return "Editor [id=" + id + ", nameEditor=" + nameEditor + ", addressEditor=" + addressEditor + "]";
	}

}
