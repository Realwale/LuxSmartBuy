package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.dto.ProductDTO;
import com.charisplace.luxsmartbuy.config.ApiResponse;
import com.charisplace.luxsmartbuy.model.Category;
import com.charisplace.luxsmartbuy.service.CategoryService;
import com.charisplace.luxsmartbuy.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDTO productDTO){
        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Invalid category"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDTO, category);
        return  new ResponseEntity<>(new ApiResponse(true, "Product added successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> productDTOS = productService.listProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestBody @Valid ProductDTO productDTO){
        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());

        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Invalid category"), HttpStatus.CONFLICT);
        }

        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDTO, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product updated successfully"), HttpStatus.OK);
    }
}
