package com.tcs.product_service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.product_service.entities.Category;
import com.tcs.product_service.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;
    
    public CategoryController(CategoryService service) {
		super();
		this.service = service;
	}

	@PostMapping
    public Category add(@RequestBody Category category) {
        return service.save(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }
}

