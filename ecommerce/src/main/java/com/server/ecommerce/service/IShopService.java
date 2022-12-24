package com.server.ecommerce.service;

import com.server.ecommerce.model.Shop;
import com.server.ecommerce.model.ShopCategory;

import java.util.Set;

public interface IShopService {
    Set<Shop> getAllVerifiedAndActiveShops();
    void createShop(Shop shop);
    Shop getShopByIdAndVerifiedTrueAndActiveTrue(Long id);
}
