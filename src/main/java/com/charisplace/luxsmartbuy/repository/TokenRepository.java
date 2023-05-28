package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.AuthenticationToken;
import com.charisplace.luxsmartbuy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findTokenByUser(User user);

    AuthenticationToken findTokenByToken(String token);
}
