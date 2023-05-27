package com.charisplace.luxsmartbuy.controller;

import com.charisplace.luxsmartbuy.dto.users.SignUpResponseDTO;
import com.charisplace.luxsmartbuy.dto.users.SignupDTO;
import com.charisplace.luxsmartbuy.exceptions.CustomException;
import com.charisplace.luxsmartbuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDTO Signup(@RequestBody SignupDTO signupDTO) throws CustomException {
        return userService.signUp(signupDTO);
    }
}
