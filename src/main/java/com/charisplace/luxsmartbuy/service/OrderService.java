package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OrderService {
    Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException;

}
