package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.repository.UserRepository;
import com.charisplace.luxsmartbuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
