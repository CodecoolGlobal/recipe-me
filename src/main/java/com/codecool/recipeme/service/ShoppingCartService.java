package com.codecool.recipeme.service;

import com.codecool.recipeme.model.RMIngredientsItem;
import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.ShoppingCart;
import com.codecool.recipeme.model.generated.IngredientsItem;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.IngredientsItemRepository;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.repository.RecipeRepository;
import com.codecool.recipeme.repository.ShoppingCartRepository;
import com.codecool.recipeme.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    Utils utils;


    public List<RMIngredientsItem> getItemsFromShoppingCart() {
        ShoppingCart shoppingCart = getActualCart();
        return shoppingCart == null ? new ArrayList<>() : new ArrayList<>(shoppingCart.getIngredients());
    }

    @Transactional
    public void addRecipeIngredientsToShoppingCart(Recipe recipe) {
        RMRecipe rmRecipe = new RMRecipe(recipe);
        RecipeMeUser user = utils.getUserFromContext();
        ShoppingCart shoppingCart = user.getShoppingCart();
        //getItemsFromShoppingCart().addAll(rmRecipe.getIngredients());
        for (RMIngredientsItem rmIngredientsItem: rmRecipe.getIngredients()) {
            ingredientsItemRepository.saveAndFlush(rmIngredientsItem);
            shoppingCart.addIngredient(rmIngredientsItem);
        }
        shoppingCartRepository.saveAndFlush(shoppingCart);

    }

    private ShoppingCart getActualCart() {
        RecipeMeUser user = utils.getUserFromContext();
        Optional<RecipeMeUser> recipeMeUser = recipeMeUserRepository.findByName(user.getName());
        return recipeMeUser.isEmpty() ? null : recipeMeUser.get().getShoppingCart();
    }
}
