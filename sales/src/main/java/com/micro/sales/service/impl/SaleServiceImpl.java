package com.micro.sales.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.sales.client.ProductClient;
import com.micro.sales.dto.ProductResponse;
import com.micro.sales.dto.SaleRequest;
import com.micro.sales.dto.SaleResponse;
import com.micro.sales.model.Sale;
import com.micro.sales.repository.SaleRepository;
import com.micro.sales.service.ISaleService;
import com.micro.sales.utils.constants.ResponseMessages;
import com.micro.sales.utils.response.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final SaleRepository saleRepository;
    private final ProductClient productClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseCustom<SaleResponse> createSale(SaleRequest request) {
        List<ProductResponse> products = new ArrayList<>();

        for (Long id : request.getProductIds()) {
            ResponseEntity<ProductResponse> response = productClient.getProductById(id);

            if (response.getBody() == null) {
                return ResponseCustom.<SaleResponse>builder()
                        .code(ResponseMessages.NOT_FOUND_CODE)
                        .title(ResponseMessages.ERROR_TITLE)
                        .description(String.format(ResponseMessages.PRODUCT_NOT_FOUND, id))
                        .messageButton(ResponseMessages.REVIEW_PRODUCTS_BUTTON)
                        .build();
            }

            products.add(response.getBody());
        }

        Double total = products.stream().mapToDouble(ProductResponse::getPrice).sum();
        String description = products.stream()
                .map(p -> p.getName() + " (" + p.getDescription() + ")")
                .collect(Collectors.joining(", "));

        Sale sale = Sale.builder()
                .productsDescription(description)
                .total(total)
                .createdAt(LocalDateTime.now())
                .build();

        Sale saved = saleRepository.save(sale);

        SaleResponse response = SaleResponse.builder()
                .id(saved.getId())
                .productsDescription(saved.getProductsDescription())
                .total(saved.getTotal())
                .createdAt(saved.getCreatedAt())
                .build();

        return ResponseCustom.<SaleResponse>builder()
                .code(ResponseMessages.CREATED_CODE)
                .title(ResponseMessages.SUCCESS_TITLE)
                .description(ResponseMessages.SALE_CREATED)
                .messageButton(ResponseMessages.OK_BUTTON)
                .data(response)
                .build();
    }


    @Override
    public ResponseCustom<List<SaleResponse>> getAllSales() {
        List<SaleResponse> sales = saleRepository.findAll().stream()
                .map(s -> SaleResponse.builder()
                        .id(s.getId())
                        .productsDescription(s.getProductsDescription())
                        .total(s.getTotal())
                        .createdAt(s.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        if (sales.isEmpty()) {
            return ResponseCustom.<List<SaleResponse>>builder()
                    .code(ResponseMessages.NO_CONTENT_CODE)
                    .title(ResponseMessages.NO_DATA_TITLE)
                    .description(ResponseMessages.SALES_NOT_FOUND)
                    .messageButton(ResponseMessages.BACK_BUTTON)
                    .data(sales)
                    .build();
        }

        return ResponseCustom.<List<SaleResponse>>builder()
                .code(ResponseMessages.SUCCESS_CODE)
                .title(ResponseMessages.SUCCESS_TITLE)
                .description(ResponseMessages.SALES_FOUND)
                .messageButton(ResponseMessages.OK_BUTTON)
                .data(sales)
                .build();
    }

    @Override
    public ResponseCustom<SaleResponse> updateSale(Long id, SaleRequest request) {
        Sale sale = saleRepository.findById(id).orElse(null);

        if (sale == null) {
            return ResponseCustom.<SaleResponse>builder()
                    .code(ResponseMessages.NOT_FOUND_CODE)
                    .title(ResponseMessages.ERROR_TITLE)
                    .description(String.format(ResponseMessages.SALE_NOT_FOUND, id))
                    .messageButton(ResponseMessages.REVIEW_BUTTON)
                    .build();
        }

        List<ProductResponse> products = new ArrayList<>();
        for (Long pid : request.getProductIds()) {
            ResponseEntity<ProductResponse> response = productClient.getProductById(pid);

            if (response.getBody() == null) {
                return ResponseCustom.<SaleResponse>builder()
                        .code(ResponseMessages.NOT_FOUND_CODE)
                        .title(ResponseMessages.ERROR_TITLE)
                        .description(String.format(ResponseMessages.PRODUCT_NOT_FOUND, pid))
                        .messageButton(ResponseMessages.REVIEW_PRODUCTS_BUTTON)
                        .build();
            }

            products.add(response.getBody());
        }

        Double total = products.stream().mapToDouble(ProductResponse::getPrice).sum();
        String description = products.stream()
                .map(p -> p.getName() + " (" + p.getDescription() + ")")
                .collect(Collectors.joining(", "));

        sale.setProductsDescription(description);
        sale.setTotal(total);

        Sale updated = saleRepository.save(sale);

        SaleResponse response = SaleResponse.builder()
                .id(updated.getId())
                .productsDescription(updated.getProductsDescription())
                .total(updated.getTotal())
                .createdAt(updated.getCreatedAt())
                .build();

        return ResponseCustom.<SaleResponse>builder()
                .code(ResponseMessages.SUCCESS_CODE)
                .title(ResponseMessages.SUCCESS_TITLE)
                .description(ResponseMessages.SALE_UPDATED)
                .messageButton(ResponseMessages.OK_BUTTON)
                .data(response)
                .build();
    }

}
