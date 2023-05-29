package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.charisplace.luxsmartbuy.service.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

//    @Autowired
//    private CartService cartService;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDTO) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount( ((long) checkoutItemDTO.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDTO.getProductName())
                                .build())
                .build();

    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDTO){
        return SessionCreateParams.LineItem.builder()
                .setPriceData((createPriceData(checkoutItemDTO)))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDTO.getQuantity())))
                .build();
    }


    @Override
    public Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException {
        String successURL = baseURL + "payment/success";

        String failedUrl = baseURL + "payment/failed";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        for (CheckoutItemDTO checkoutItemDTO : checkoutItemDTOList){
            sessionItemsList.add(createSessionLineItem(checkoutItemDTO));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedUrl)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }


}
