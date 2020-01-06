package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.generated.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, Long> {
}
