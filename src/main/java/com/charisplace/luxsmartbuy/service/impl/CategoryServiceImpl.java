package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.model.Category;
import com.charisplace.luxsmartbuy.repository.CategoryRepository;
import com.charisplace.luxsmartbuy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category readCategory(String categoryName) {
       return categoryRepository.findByCategoryName(categoryName) ;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> readCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public void updateCategory(Long categoryID, Category newCategory) {
        Category category = categoryRepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }

}
