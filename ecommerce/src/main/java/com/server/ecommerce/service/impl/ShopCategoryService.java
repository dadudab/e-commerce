package com.server.ecommerce.service.impl;

import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.entity.ShopCategory;
import com.server.ecommerce.repository.ShopCategoryRepository;
import com.server.ecommerce.service.IShopCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShopCategoryService implements IShopCategoryService {
    private final ShopCategoryRepository shopCategoryRepository;

    @Override
    public Set<ShopCategory> getAllShopCategories() {
        return new HashSet<>(shopCategoryRepository.findAll());
    }

    @Override
    public ShopCategory getShopCategoryById(Long id) {
        return shopCategoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Shop category not found"));
    }

    @Override
    public void createShopCategory(ShopCategory shopCategory) {
        shopCategoryRepository.save(shopCategory);
    }
}
