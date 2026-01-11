package com.tcs.product_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.product_service.entities.Category;
import com.tcs.product_service.repositories.CategoryRepository;

@Service

public class CategoryService {

    private final CategoryRepository repository;
    

    public CategoryService(CategoryRepository repository) {
		super();
		this.repository = repository;
	}

	public Category save(Category category) {
        return repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }
}

