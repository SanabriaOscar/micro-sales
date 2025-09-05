package com.micro.products.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/products")
public interface ProductApi {

    @GetMapping
    public ResponseEntity<Object> listAllProducts();

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdProduct(@PathVariable Long id);

}
