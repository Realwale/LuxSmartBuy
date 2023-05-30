package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.config.ApiResponse;
import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.charisplace.luxsmartbuy.dto.StripeResponse;
import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.model.Order;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.service.AuthenticationService;
import com.charisplace.luxsmartbuy.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException{

        Session session = orderService.createSession(checkoutItemDTOList);

        StripeResponse stripeResponse = new StripeResponse(session.getId());

        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId) throws AuthenticationFailException {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        orderService.placeOrder(user, sessionId);

        return new ResponseEntity<>(new ApiResponse(true, "Order placed successfully"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        List<Order> orderList = orderService.listOrders(user);

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id, @RequestParam("token") String token) throws AuthenticationFailException {


        authenticationService.authenticate(token);

       User user = authenticationService.getUser(token);

        Order order = orderService.getOrder(id, user);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
