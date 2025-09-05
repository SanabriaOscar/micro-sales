package com.micro.products.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/categories")
public interface CategoryApi {

    @GetMapping
    public ResponseEntity<Object> listAllCategories();

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCategory(@PathVariable Long id);

}
