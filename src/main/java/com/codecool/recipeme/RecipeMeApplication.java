package com.codecool.recipeme;

import com.codecool.recipeme.model.RMIngredientsItem;
import com.codecool.recipeme.model.RMRecipe;
import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.ShoppingCart;
import com.codecool.recipeme.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class RecipeMeApplication {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(RecipeMeApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            RMIngredientsItem rmIngredientsItem = new RMIngredientsItem();
            rmIngredientsItem.setText("some coriander");

            RMRecipe recipe = RMRecipe.builder()
                    .label("pho")
                    .ingredient(rmIngredientsItem)
                    .build();

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.addIngredient(rmIngredientsItem);

            RecipeMeUser admin = RecipeMeUser.builder()
                    .name("admin")
                    .roles(Arrays.asList("ADMIN"))
                    .password(passwordEncoder.encode("admin"))
                    .favourite(recipe)
                    .shoppingCart(shoppingCart)
                    .build();

            recipeMeUserRepository.saveAndFlush(admin);
        };
    }
}