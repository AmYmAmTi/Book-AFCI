package com.nathan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping("/editors")
public class EditorController {

   

	// ctrl + space + G -> @GetMapping
	
	// ============================
    // 1. List All Editors
    // ============================
	@GetMapping
	public String getAllEditors() {
		return "editors";
	}
	
	// ============================
    // 2. View Book Details
    // ============================
	
	@GetMapping("/{id}")
	public String getEditorById(@PathVariable Long id) {
	
		System.out.println("Editor id:"+id);
		
		return "editorDetails";
	}
	
	// ============================
    // 3. Show Form to Add Editor
    // ============================
	
	@GetMapping("/add")
	public String getAddForm(@PathVariable Long id) {
		
		System.out.println("Editor id:"+id);
		
		return"editorAddForm";
	}
	
	
	
	
	
	
	
	// ============================
    // 5. Show Form to Edit Editor
    // ============================
	@GetMapping("edit/{id}")
	public String getEdit(@PathVariable Long id) {
	
		System.out.println("Editor id:"+id);
		
		return "editorEdit";
	}
	
	
	
	
	
	
	
	
	
}
