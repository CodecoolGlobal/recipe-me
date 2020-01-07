package com.codecool.recipeme;

import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.ShoppingCart;
import com.codecool.recipeme.model.generated.IngredientsItem;
import com.codecool.recipeme.model.generated.Recipe;
import com.codecool.recipeme.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class RecipeMeApplication {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

     @Autowired
    IngredientsItemRepository ingredientsItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(RecipeMeApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Recipe recipe = Recipe.builder()
                    .label("pho")
                    .build();
            recipeRepository.saveAndFlush(recipe);
            IngredientsItem ing = IngredientsItem.builder()
                    .text("só")
                    .recipe(recipe)
                    .build();
            ingredientsItemRepository.saveAndFlush(ing);
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .ingredient(ing)
                    .build();
            shoppingCartRepository.saveAndFlush(shoppingCart);
            RecipeMeUser recipeMeUser = RecipeMeUser.builder()
                    .name("Panna")
                    .shoppingCart(shoppingCart)
                    .favourite(recipe)
                    .build();
            userRepository.saveAndFlush(recipeMeUser);
        };
    }
}