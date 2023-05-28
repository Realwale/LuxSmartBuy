package com.charisplace.luxsmartbuy.service;

import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.model.AuthenticationToken;
import com.charisplace.luxsmartbuy.model.User;

public interface AuthenticationService {

    void saveConfirmationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    User getUser(String token);

    void authenticate(String token) throws AuthenticationFailException;
}
