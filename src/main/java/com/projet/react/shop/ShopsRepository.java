package com.projet.react.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopsRepository extends JpaRepository<Shops,Long> {
    Shops findShopById(Long id);
}
