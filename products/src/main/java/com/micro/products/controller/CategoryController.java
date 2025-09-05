package com.micro.products.controller;

import com.micro.products.controller.api.CategoryApi;
import com.micro.products.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<Object> listAllCategories() {
        return  ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Override
    public ResponseEntity<Object> findByIdCategory(Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
