package com.server.ecommerce.repository;

import com.server.ecommerce.model.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Long> {
}
