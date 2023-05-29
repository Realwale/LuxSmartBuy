package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.AddToCartDTO;
import com.charisplace.luxsmartbuy.dto.CartDTO;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.model.User;

public interface CartService {
    void addToCart(AddToCartDTO addToCartDTO, Product product, User user);

    CartDTO listCartItems(User user);
}
