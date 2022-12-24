package com.server.ecommerce.controller;

import com.server.ecommerce.model.Shop;
import com.server.ecommerce.model.ShopCategory;
import com.server.ecommerce.service.impl.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/shops")
    public Set<Shop> getAllVerifiedAndActiveShops() {
        return shopService.getAllVerifiedAndActiveShops();
    }

    @GetMapping("/shops/{shopId}")
    public Shop getVerifiedAndActiveShopById(@PathVariable Long id) {
        return shopService.getShopByIdAndVerifiedTrueAndActiveTrue(id);
    }

    @PostMapping("/shops/new")
    public void addShop(@RequestBody Shop shop) {
        shopService.createShop(shop);
    }
}
