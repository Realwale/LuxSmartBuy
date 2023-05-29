package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.AddToCartDTO;
import com.charisplace.luxsmartbuy.model.Cart;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.repository.CartRepository;
import com.charisplace.luxsmartbuy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addToCart(AddToCartDTO addToCartDTO, Product product, User user) {

        Cart cart = new Cart(product, addToCartDTO.getQuantity(), user);

        cartRepository.save(cart);
    }
}
