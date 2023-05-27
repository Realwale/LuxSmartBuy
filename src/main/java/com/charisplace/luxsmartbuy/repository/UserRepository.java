package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
