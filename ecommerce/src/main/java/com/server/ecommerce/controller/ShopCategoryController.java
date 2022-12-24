package com.server.ecommerce.controller;

import com.server.ecommerce.model.ShopCategory;
import com.server.ecommerce.service.impl.ShopCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ShopCategoryController {
    private final ShopCategoryService shopCategoryService;

    @GetMapping("/shop-categories")
    public Set<ShopCategory> getAllShopCategories() {
        return shopCategoryService.getAllShopCategories();
    }

    @GetMapping("/shop-categories/{shopCategoryId}")
    public ShopCategory getShopCategoryById(@PathVariable Long shopCategoryId) {
        return shopCategoryService.getShopCategoryById(shopCategoryId);
    }

    @PostMapping("/shop-categories/new")
    public void addShopCategory(@RequestBody ShopCategory shopCategory) {
        shopCategoryService.createShopCategory(shopCategory);
    }
}
