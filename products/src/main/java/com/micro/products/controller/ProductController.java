package com.micro.products.controller;

import com.micro.products.controller.api.ProductApi;
import com.micro.products.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<Object> listAllProducts() {

        return  ResponseEntity.ok(productService.getAllProducts());
    }

    @Override
    public ResponseEntity<Object> findByIdProduct(Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
