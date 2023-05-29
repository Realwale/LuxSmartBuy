package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.charisplace.luxsmartbuy.dto.StripeResponse;
import com.charisplace.luxsmartbuy.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException{

        Session session = orderService.createSession(checkoutItemDTOList);

        StripeResponse stripeResponse = new StripeResponse(session.getId());

        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

}
