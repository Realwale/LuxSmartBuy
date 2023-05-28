package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.model.WishList;
import com.charisplace.luxsmartbuy.repository.WishListRepository;
import com.charisplace.luxsmartbuy.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    @Override
    public List<WishList> readWishList(User user) {
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
