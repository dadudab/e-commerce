package com.server.ecommerce.service;

import com.server.ecommerce.entity.Shop;

import java.util.Set;

public interface IShopService {
    Set<Shop> getAllVerifiedAndActiveShops();
    void createShop(Shop shop);
    Shop getShopByIdAndVerifiedTrueAndActiveTrue(Long id);
}
