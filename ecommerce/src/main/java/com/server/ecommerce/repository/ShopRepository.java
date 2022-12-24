package com.server.ecommerce.repository;

import com.server.ecommerce.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Set<Shop> findAllByVerifiedTrueAndActiveTrue();
    Optional<Shop> findByIdAndVerifiedTrueAndActiveTrue(Long id);
}
