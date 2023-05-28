package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.config.MessageStrings;
import com.charisplace.luxsmartbuy.exceptions.AuthenticationFailException;
import com.charisplace.luxsmartbuy.model.AuthenticationToken;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.repository.TokenRepository;
import com.charisplace.luxsmartbuy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    TokenRepository repository;


    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }

    @Override
    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);

        if (Objects.nonNull(authenticationToken)){
            if (Objects.nonNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }


    @Override
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }

    }
}
