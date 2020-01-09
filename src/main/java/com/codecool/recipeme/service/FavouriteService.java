package com.codecool.recipeme.service;

import com.codecool.recipeme.model.Favourite;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.FavouriteRepository;
import com.codecool.recipeme.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavouriteService {

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> getRecipesFromFavourites(Long userId) {
        return recipeRepository.findAllByFavouriteId(userId);
    }

    public void addRecipesToFavourites(Recipe recipe) {

        Favourite favourite = favouriteRepository.getOne(2L);
        recipe.setFavourite(favourite);
        recipeRepository.saveAndFlush(recipe);;
    }
}

