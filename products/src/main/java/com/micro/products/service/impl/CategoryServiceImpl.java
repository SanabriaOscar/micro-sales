package com.micro.products.service.impl;

import com.micro.products.exceptions.type.CategoryException;
import com.micro.products.model.Category;
import com.micro.products.repository.CategoryRepository;
import com.micro.products.service.ICategoryService;
import com.micro.products.utils.constants.ResponseMessages;
import com.micro.products.utils.response.ResponseCustom;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseCustom<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return ResponseCustom.<List<Category>>builder()
                    .code(204)
                    .description(ResponseMessages.CATEGORIES_NOT_FOUND)
                    .data(categories)
                    .build();
        }

        return ResponseCustom.<List<Category>>builder()
                .code(200)
                .description(ResponseMessages.CATEGORIES_FOUND)
                .data(categories)
                .build();
    }


    @Override
    public ResponseCustom<Category> getCategoryById(Long id) throws CategoryException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(
                        ResponseCustom.builder()
                                .code(204)
                                .description(ResponseMessages.CATEGORY_NOT_FOUND)
                                .build()
                ));
        return ResponseCustom.<Category>builder()
                .code(200)
                .description(ResponseMessages.CATEGORY_FOUND)
                .data(category)
                .build();
    }
}
