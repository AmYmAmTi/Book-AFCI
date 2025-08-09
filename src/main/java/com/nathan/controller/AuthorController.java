package com.nathan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/authors")
public class AuthorController {

	// ctrl + space + G -> @GetMapping

	// ============================
	// 1. List All Authors
	// ============================
	@GetMapping
	public String getAllAuthors() {
		return "authorsList";
	}

	// ============================
	// 2. View Book Details
	// ============================

	@GetMapping("/{id}")
	public String getAuthorById(@PathVariable Long id) {

		System.out.println("Author id:" + id);

		return "authorDetails";
	}

	// ============================
	// 3. Show Form to Add Author
	// ============================

	@GetMapping("/add")
	public String getAdd(@PathVariable Long id) {

		System.out.println("Author id:" + id);

		return "authorAdd";
	}

	// ============================
	// 5. Show Form to Edit Author
	// ============================
	@GetMapping("edit/{id}")
	public String getEdit(@PathVariable Long id) {

		System.out.println("Author id:" + id);

		return "authorEdit";
	}

}
