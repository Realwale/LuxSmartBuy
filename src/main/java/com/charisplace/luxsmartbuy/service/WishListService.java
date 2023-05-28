package com.charisplace.luxsmartbuy.service;


import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.model.WishList;

import java.util.List;

public interface WishListService {

    void createWishList(WishList wishList);

    List<WishList> readWishList(User user);
}
