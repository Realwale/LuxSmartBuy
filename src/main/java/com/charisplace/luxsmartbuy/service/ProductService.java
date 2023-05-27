package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.ProductDTO;
import com.charisplace.luxsmartbuy.model.Category;

import java.util.List;

public interface ProductService {


    void addProduct(ProductDTO productDTO, Category category);

    List<ProductDTO> listProducts();

    void updateProduct(Long productID, ProductDTO productDTO, Category category);

}
