package com.server.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String nip;
    private boolean verified;
    private boolean active;
    @ManyToMany
    @JoinTable(
            name = "shop_shop_category",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shop_category_id", referencedColumnName = "id")
    )
    private Set<ShopCategory> shopCategories;
}
