package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.ProductDTO;
import com.charisplace.luxsmartbuy.exceptions.ProductNotExistException;
import com.charisplace.luxsmartbuy.model.Category;
import com.charisplace.luxsmartbuy.model.Product;

import java.util.List;

public interface ProductService {


    void addProduct(ProductDTO productDTO, Category category);

    List<ProductDTO> listProducts();

    void updateProduct(Long productID, ProductDTO productDTO, Category category);

    Product getProductById(Long productId) throws ProductNotExistException;
}
