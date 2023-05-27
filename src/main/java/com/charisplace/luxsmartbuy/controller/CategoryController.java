package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.config.ApiResponse;
import com.charisplace.luxsmartbuy.model.Category;
import com.charisplace.luxsmartbuy.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<>(new ApiResponse(false, "category already exists!"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody Category category){

        if(Objects.nonNull(categoryService.readCategory(categoryID))){
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>(new ApiResponse(true, "category successfully updated"), HttpStatus.OK);

        }
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist!"), HttpStatus.NOT_FOUND);
    }


}
