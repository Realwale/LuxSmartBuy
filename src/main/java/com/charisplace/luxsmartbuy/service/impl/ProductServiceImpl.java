package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.ProductDTO;
import com.charisplace.luxsmartbuy.exceptions.ProductNotExistException;
import com.charisplace.luxsmartbuy.model.Category;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.repository.ProductRepository;
import com.charisplace.luxsmartbuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(ProductDTO productDTO, Category category) {
        Product product = getProductFromDTO(productDTO, category);
        productRepository.save(product);

    }

    @Override
    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products){
            productDTOS.add(new ProductDTO(product));
        }
        return productDTOS;
    }

    @Override
    public void updateProduct(Long productID, ProductDTO productDTO, Category category) {
        Product product = getProductFromDTO(productDTO, category);

        product.setId(productID);

        productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);

        return optionalProduct.get();
    }


    public static Product getProductFromDTO(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        return product;
    }
}
