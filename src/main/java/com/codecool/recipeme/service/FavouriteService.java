package com.codecool.recipeme.service;

import com.codecool.recipeme.model.Favourite;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.FavouriteRepository;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.repository.RecipeRepository;
import com.codecool.recipeme.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavouriteService {

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    SessionData sessionData;

    public List<Recipe> getRecipesFromFavourites() {
        RecipeMeUser recipeMeUser = sessionData.getRecipeMeUserFromCookie();
        return recipeRepository.findAllByFavouriteId(recipeMeUser.getId());
    }

    public void addRecipesToFavourites(Recipe recipe) {

        Favourite favourite = favouriteRepository.getOne(2L);
        recipe.setFavourite(favourite);
        recipeRepository.saveAndFlush(recipe);;
    }
}

