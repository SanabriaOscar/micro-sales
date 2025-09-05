package com.micro.products.service;

import com.micro.products.exceptions.type.CategoryException;
import com.micro.products.model.Category;
import com.micro.products.utils.response.ResponseCustom;

import java.util.List;

public interface ICategoryService {
    ResponseCustom<List<Category>> getAllCategories() throws CategoryException;
    ResponseCustom<Category> getCategoryById(Long id) throws CategoryException;
}
