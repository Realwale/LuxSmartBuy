package com.charisplace.luxsmartbuy.repository;

import com.charisplace.luxsmartbuy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}

