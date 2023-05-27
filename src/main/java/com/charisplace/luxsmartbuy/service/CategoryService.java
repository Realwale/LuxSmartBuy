package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category readCategory(String categoryName);

    void createCategory(Category category);

    List<Category> listCategories();

    Optional<Category> readCategory(Long categoryId);

    void updateCategory(Long categoryID, Category newCategory);

}
