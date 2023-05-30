package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.charisplace.luxsmartbuy.exceptions.OrderNotFoundException;
import com.charisplace.luxsmartbuy.model.Order;
import com.charisplace.luxsmartbuy.model.User;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OrderService {
    Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException;

    void placeOrder(User user, String sessionId);

    List<Order> listOrders(User user);

    Order getOrder(Long id, User user) throws OrderNotFoundException;
}
