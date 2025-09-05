package com.micro.products.utils;
import com.micro.products.model.Category;
import com.micro.products.model.Product;

import java.util.List;

public class DataDummy {
    //Data dummy categories
    public static Category getTechCategory() {
        return new Category(1L, "Tech", "Technology", List.of());
    }
    public static Category getBooksCategory() {
        return new Category(2L, "Books", "Books Category", List.of());
    }
    public static List<Category> getCategoryList() {
        return List.of(getTechCategory(), getBooksCategory());
    }
    public static List<Category> getEmptyCategoryList() {
        return List.of();
    }
    // Data dummy products
    public static Product getLaptopProduct() {
        return new Product(1L, "Laptop", "Laptop", 1500.0, getTechCategory());
    }

    public static Product getBookProduct() {
        return new Product(2L, "Book", "Book", 100.0, getBooksCategory());
    }

    public static List<Product> getProductList() {
        return List.of(getLaptopProduct(), getBookProduct());
    }

    public static List<Product> getEmptyProductList() {
        return List.of();
    }

}
