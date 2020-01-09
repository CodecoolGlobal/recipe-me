package com.codecool.recipeme.service;

import com.codecool.recipeme.model.RMIngredientsItem;
import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.ShoppingCart;
import com.codecool.recipeme.model.generated.Recipe;
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

    public List<RMIngredientsItem> getItemsFromShoppingCart() {
        ShoppingCart shoppingCart = getActualCart();
        return shoppingCart == null ? new ArrayList<>() : new ArrayList<>(shoppingCart.getIngredients());
    }

    @Transactional
    public void addRecipeIngredientsToShoppingCart(Recipe recipe) {
        RMRecipe rmRecipe = new RMRecipe(recipe);
        getItemsFromShoppingCart().addAll(rmRecipe.getIngredients());
    }

    private ShoppingCart getActualCart() {
        RecipeMeUser user = Utils.getUserFromContext();
        Optional<RecipeMeUser> recipeMeUser = recipeMeUserRepository.findByName(user.getName());
        return recipeMeUser.isEmpty() ? null : recipeMeUser.get().getShoppingCart();
    }
}
