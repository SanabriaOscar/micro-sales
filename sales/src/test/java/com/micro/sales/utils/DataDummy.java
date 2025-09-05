package com.micro.sales.utils;

import com.micro.sales.dto.ProductResponse;
import com.micro.sales.dto.SaleRequest;
import com.micro.sales.model.Sale;

import java.time.LocalDateTime;
import java.util.List;

public class DataDummy {

    public static ProductResponse getLaptopProduct() {
        return ProductResponse.builder()
                .id(1L)
                .name("Laptop")
                .description("Gaming Laptop")
                .price(1500.0)
                .build();
    }

    public static ProductResponse getBookProduct() {
        return ProductResponse.builder()
                .id(2L)
                .name("Book")
                .description("Java Book")
                .price(100.0)
                .build();
    }

    public static Sale getSaleEntity() {
        return Sale.builder()
                .id(1L)
                .productsDescription("Laptop (Gaming Laptop)")
                .total(1500.0)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<Sale> getSaleList() {
        return List.of(getSaleEntity());
    }

    public static List<Sale> getEmptySaleList() {
        return List.of();
    }

    public static SaleRequest getSaleRequestWithProducts() {
        return SaleRequest.builder()
                .productIds(List.of(1L, 2L))
                .build();
    }
}
