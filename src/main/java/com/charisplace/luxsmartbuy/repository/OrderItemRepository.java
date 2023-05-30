package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
