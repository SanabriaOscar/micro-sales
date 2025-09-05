package com.micro.sales.controller;

import com.micro.sales.controller.api.SaleApi;
import com.micro.sales.dto.SaleRequest;
import com.micro.sales.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaleController implements SaleApi {

    private final ISaleService saleService;

    @Override
    public ResponseEntity<Object> createSale(SaleRequest request) {
        return ResponseEntity.ok(saleService.createSale(request));
    }

    @Override
    public ResponseEntity<Object> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @Override
    public ResponseEntity<Object> updateSale(Long id, SaleRequest request) {
        return ResponseEntity.ok(saleService.updateSale(id, request));
    }
}
