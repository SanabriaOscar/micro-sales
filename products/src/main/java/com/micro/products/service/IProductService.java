package com.micro.products.service;


import com.micro.products.exceptions.type.ProductException;
import com.micro.products.model.Product;
import com.micro.products.utils.response.ResponseCustom;

import java.util.List;

public interface IProductService {
    ResponseCustom<List<Product>> getAllProducts() throws ProductException;
    ResponseCustom<Product> getProductById(Long id) throws ProductException;
}

