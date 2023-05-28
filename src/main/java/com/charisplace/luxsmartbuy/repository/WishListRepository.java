package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
