package com.codecool.recipeme.service;

import com.codecool.recipeme.model.RMIngredientsItem;
import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.IngredientsItemRepository;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.repository.RecipeRepository;
import com.codecool.recipeme.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class FavouriteService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    Utils utils;

    public Set<RMRecipe> getRecipesFromFavourites() {
        RecipeMeUser user = utils.getUserFromContext();

        if (user == null) {
            return null;
        }
        Set<RMRecipe> recipes = user.getFavourites();
        return recipes;
    }

    public void addRecipeToFavourites(Recipe recipe) {
        RMRecipe rmRecipe = new RMRecipe(recipe); // TODO here or in controller?
        RecipeMeUser recipeMeUser = utils.getUserFromContext();
        recipeRepository.saveAndFlush(rmRecipe);
        for (RMIngredientsItem ingredientsItem: rmRecipe.getIngredients()) {
            ingredientsItemRepository.saveAndFlush(ingredientsItem);
        }
        if (recipeMeUser != null) {

            recipeMeUser.addFavouriteRecipe(rmRecipe);
            recipeMeUserRepository.saveAndFlush(recipeMeUser);
        }
        
    }
}

