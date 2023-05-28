package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.config.ApiResponse;
import com.charisplace.luxsmartbuy.dto.ProductDTO;
import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.model.Product;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.model.WishList;
import com.charisplace.luxsmartbuy.repository.ProductRepository;
import com.charisplace.luxsmartbuy.service.AuthenticationService;
import com.charisplace.luxsmartbuy.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDTO productDTO, @RequestParam("token") String token) throws AuthenticationFailException {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        Product product = productRepository.getById(productDTO.getId());

        WishList wishList = new WishList(user, product);

        wishListService.createWishList(wishList);

        return new ResponseEntity<>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token) throws AuthenticationFailException {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        List<WishList> wishLists = wishListService.readWishList(user);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (WishList wishList : wishLists){
            productDTOS.add(new ProductDTO(wishList.getProduct()));
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
