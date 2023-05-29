package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.AddToCartDTO;
import com.charisplace.luxsmartbuy.dto.CartDTO;
import com.charisplace.luxsmartbuy.dto.CartItemDTO;
import com.charisplace.luxsmartbuy.model.Cart;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.repository.CartRepository;
import com.charisplace.luxsmartbuy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addToCart(AddToCartDTO addToCartDTO, Product product, User user) {

        Cart cart = new Cart(product, addToCartDTO.getQuantity(), user);

        cartRepository.save(cart);
    }

    @Override
    public CartDTO listCartItems(User user) {

        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDTO> cartItemDTOS = new ArrayList<>();

        for (Cart cart : cartList){

            CartItemDTO cartItemDTO = new CartItemDTO(cart);

            cartItemDTOS.add(cartItemDTO);
        }

        double totalCost = 0;

        for (CartItemDTO cartItemDTO : cartItemDTOS){
            totalCost += cartItemDTO.getProduct().getPrice() * cartItemDTO.getQuantity();
        }

        return new CartDTO(cartItemDTOS, totalCost);
    }
}
