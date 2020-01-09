package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.RMRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RMRecipe, Long> {
}
