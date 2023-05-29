package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.dto.users.SignInDTO;
import com.charisplace.luxsmartbuy.dto.users.SignUpResponseDTO;
import com.charisplace.luxsmartbuy.dto.users.SignupDTO;
import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.exceptions.CustomException;


public interface UserService {

    SignUpResponseDTO signUp(SignupDTO signupDTO) throws CustomException;

    SignUpResponseDTO signIn(SignInDTO signInDTO) throws AuthenticationFailException, CustomException;
}
