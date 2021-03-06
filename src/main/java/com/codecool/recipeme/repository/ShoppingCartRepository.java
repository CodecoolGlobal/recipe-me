package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
