package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.RMIngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsItemRepository extends JpaRepository<RMIngredientsItem, Long> {
}
