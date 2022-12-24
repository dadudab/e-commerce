package com.server.ecommerce.service;

import com.server.ecommerce.model.ShopCategory;

import java.util.Optional;
import java.util.Set;

public interface IShopCategoryService {
    Set<ShopCategory> getAllShopCategories();
    ShopCategory getShopCategoryById(Long id);
    void createShopCategory(ShopCategory shopCategory);
}
