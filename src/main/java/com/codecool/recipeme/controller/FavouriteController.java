package com.codecool.recipeme.controller;

import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.service.FavouriteService;
import com.codecool.recipeme.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @GetMapping
    Set<RMRecipe> getRecipes() {
        return favouriteService.getRecipesFromFavourites();
    }

    @PostMapping
    void addRecipe(@RequestBody Recipe recipe) {
        favouriteService.addRecipeToFavourites(recipe);
    }

}
