package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.Order;
import com.charisplace.luxsmartbuy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
