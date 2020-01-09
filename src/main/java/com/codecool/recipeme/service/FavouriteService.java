package com.codecool.recipeme.service;

import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.repository.RecipeRepository;
import com.codecool.recipeme.util.Utils;
import com.codecool.recipeme.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<RMRecipe> getRecipesFromFavourites() {
        RecipeMeUser user = Utils.getUserFromContext();
        Optional<RecipeMeUser> recipeMeUser = recipeMeUserRepository.findByName(user.getName());
        if (recipeMeUser.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(recipeMeUser.get().getFavourites());
    }

    public void addRecipeToFavourites(Recipe recipe) {
        RMRecipe rmRecipe = new RMRecipe(recipe); // TODO here or in controller?
        RecipeMeUser recipeMeUser = Utils.getUserFromContext();
        RecipeMeUser user = recipeMeUserRepository.findByName(recipeMeUser.getName()).get();
        user.getFavourites().add(rmRecipe);
        recipeMeUserRepository.saveAndFlush(user);
    }
}

