package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.CartDTO;
import com.charisplace.luxsmartbuy.dto.CartItemDTO;
import com.charisplace.luxsmartbuy.dto.CheckoutItemDTO;
import com.charisplace.luxsmartbuy.exceptions.OrderNotFoundException;
import com.charisplace.luxsmartbuy.model.Order;
import com.charisplace.luxsmartbuy.model.OrderItem;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.repository.OrderItemRepository;
import com.charisplace.luxsmartbuy.repository.OrderRepository;
import com.charisplace.luxsmartbuy.service.CartService;
import com.charisplace.luxsmartbuy.service.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

    @Override
    public void placeOrder(User user, String sessionId) {

        CartDTO cartDTO = cartService.listCartItems(user);

        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItemDTOS();

        Order newOrder = new Order();

        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDTO.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDTO cartItemDTO : cartItemDTOList){

            OrderItem orderItem = new OrderItem();

            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDTO.getProduct().getPrice());
            orderItem.setProduct(cartItemDTO.getProduct());
            orderItem.setQuantity(cartItemDTO.getQuantity());
            orderItem.setOrder(newOrder);

            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    @Override
    public Order getOrder(Long id, User user) throws OrderNotFoundException {

        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()){
            throw new OrderNotFoundException("order id is invalid");
        }

        Order order = optionalOrder.get();

        if (order.getUser() != user){
            throw new OrderNotFoundException("Oder does not belong to this user");
        }

        return order;
    }


}
