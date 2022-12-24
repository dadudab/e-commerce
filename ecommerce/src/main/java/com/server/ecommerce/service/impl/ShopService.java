package com.server.ecommerce.service.impl;

import com.server.ecommerce.exception.BadDataException;
import com.server.ecommerce.exception.DataNotFoundException;
import com.server.ecommerce.model.Shop;
import com.server.ecommerce.model.ShopCategory;
import com.server.ecommerce.repository.ShopRepository;
import com.server.ecommerce.service.IShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopService implements IShopService {
    private final ShopRepository shopRepository;
    private final ShopCategoryService shopCategoryService;

    @Override
    public Set<Shop> getAllVerifiedAndActiveShops() {
        return new HashSet<>(shopRepository.findAllByVerifiedTrueAndActiveTrue());
    }

    @Override
    public void createShop(Shop shop) {
        if (shop.getShopCategories().size() == 0) {
            throw new BadDataException("You have to select at least one shop category");
        }

        Set<ShopCategory> newShopCategories = shop.getShopCategories().stream()
                .map(shopCategory -> shopCategoryService.getShopCategoryById(shopCategory.getId()))
                .collect(Collectors.toSet());

        Shop newShop = Shop.builder()
                .name(shop.getName())
                .description(shop.getName())
                .nip(shop.getNip())
                .shopCategories(newShopCategories)
                .active(true)
                .verified(true)
                .build();
        shopRepository.save(newShop);
    }

    @Override
    public Shop getShopByIdAndVerifiedTrueAndActiveTrue(Long id) {
        return shopRepository.findByIdAndVerifiedTrueAndActiveTrue(id)
                .orElseThrow(() -> new DataNotFoundException("Shop not found"));
    }

}
