package com.micro.products.service.impl;

import com.micro.products.exceptions.type.ProductException;
import com.micro.products.model.Product;
import com.micro.products.repository.ProductRepository;
import com.micro.products.service.IProductService;
import com.micro.products.utils.constants.ResponseMessages;
import com.micro.products.utils.response.ResponseCustom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseCustom<List<Product>> getAllProducts() {
        List<Product> products = productRepo.findAll();

        if (products.isEmpty()) {
            return ResponseCustom.<List<Product>>builder()
                    .code(204)
                    .description(ResponseMessages.PRODUCTS_NOT_FOUND)
                    .data(products)
                    .build();
        }

        return ResponseCustom.<List<Product>>builder()
                .code(200)
                .description(ResponseMessages.PRODUCTS_FOUND)
                .data(products)
                .build();
    }

    @Override
    public ResponseCustom<Product> getProductById(Long id) throws ProductException {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new  ProductException(
                        ResponseCustom.builder()
                                .code(204)
                                .description(ResponseMessages.PRODUCT_NOT_FOUND)
                                .build()
                        ));
        return ResponseCustom.<Product>builder()
                .code(200)
                .description(ResponseMessages.PRODUCT_FOUND)
                .data(product)
                .build();

    }
}
