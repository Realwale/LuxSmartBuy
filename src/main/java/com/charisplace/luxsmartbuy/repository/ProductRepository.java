package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
