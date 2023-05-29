package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.config.ApiResponse;
import com.charisplace.luxsmartbuy.dto.AddToCartDTO;
import com.charisplace.luxsmartbuy.dto.CartDTO;
import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.exceptions.ProductNotExistException;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.service.AuthenticationService;
import com.charisplace.luxsmartbuy.service.CartService;
import com.charisplace.luxsmartbuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("token") String token) throws ProductNotExistException, AuthenticationFailException {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        Product product = productService.getProductById(addToCartDTO.getProductId());

        cartService.addToCart(addToCartDTO, product, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException{

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        CartDTO cartDTO = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
