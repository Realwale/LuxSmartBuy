package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.Cart;
import com.charisplace.luxsmartbuy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
