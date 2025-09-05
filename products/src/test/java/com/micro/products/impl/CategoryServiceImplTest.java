package com.micro.products.impl;

import com.micro.products.exceptions.type.CategoryException;
import com.micro.products.model.Category;
import com.micro.products.repository.CategoryRepository;
import com.micro.products.service.impl.CategoryServiceImpl;
import com.micro.products.utils.DataDummy;
import com.micro.products.utils.constants.ResponseMessages;
import com.micro.products.utils.response.ResponseCustom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void getAllCategories_ShouldReturn204_WhenNoCategories() {
        when(categoryRepository.findAll()).thenReturn(DataDummy.getEmptyCategoryList());

        ResponseCustom<List<Category>> response = categoryService.getAllCategories();

        assertEquals(204, response.getCode());
        assertEquals(ResponseMessages.CATEGORIES_NOT_FOUND, response.getDescription());
        assertTrue(response.getData().isEmpty());
    }

    @Test
    void getAllCategories_ShouldReturn200_WhenCategoriesExist() {
        when(categoryRepository.findAll()).thenReturn(DataDummy.getCategoryList());

        ResponseCustom<List<Category>> response = categoryService.getAllCategories();

        assertEquals(200, response.getCode());
        assertFalse(response.getData().isEmpty());
        assertEquals("Tech", response.getData().get(0).getName());
    }

    @Test
    void getCategoryById_ShouldReturn200_WhenCategoryExists() throws CategoryException {
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(DataDummy.getBooksCategory()));
        ResponseCustom<Category> response = categoryService.getCategoryById(2L);

        assertEquals(200, response.getCode());
        assertEquals("Books", response.getData().getName());
    }

    @Test
    void getCategoryById_ShouldThrowException_WhenCategoryNotFound() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        CategoryException ex = assertThrows(CategoryException.class, () -> categoryService.getCategoryById(99L));

        assertEquals(204, ex.getResponse().getCode());
        assertEquals(ResponseMessages.CATEGORY_NOT_FOUND, ex.getResponse().getDescription());
    }
}
