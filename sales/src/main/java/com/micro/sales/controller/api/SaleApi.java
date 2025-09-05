package com.micro.sales.controller.api;

import com.micro.sales.dto.SaleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/sales")
public interface SaleApi {

    @PostMapping
    ResponseEntity<Object> createSale(@RequestBody SaleRequest request);

    @GetMapping
    ResponseEntity<Object> getAllSales();

    @PutMapping("/{id}")
    ResponseEntity<Object> updateSale(@PathVariable Long id, @RequestBody SaleRequest request);
}

