package com.codecool.recipeme.controller;

import com.codecool.recipeme.model.RMIngredientsItem;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping
    List<RMIngredientsItem> getItems() {
        return shoppingCartService.getItemsFromShoppingCart();
    }

    @PostMapping
    void addItemsToShoppingCart(@RequestBody Recipe recipe) {
        shoppingCartService.addRecipeIngredientsToShoppingCart(recipe);
    }
}
