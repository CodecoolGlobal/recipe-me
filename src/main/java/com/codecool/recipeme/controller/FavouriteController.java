package com.codecool.recipeme.controller;

import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

    @GetMapping
    List<Recipe> getRecipes() {
        return favouriteService.getRecipesFromFavourites();
    }

    @PostMapping
    void addRecipe(@RequestBody Recipe recipe) {
        favouriteService.addRecipesToFavourites(recipe);
    }
}
