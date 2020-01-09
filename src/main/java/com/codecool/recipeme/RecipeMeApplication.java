package com.codecool.recipeme;

import com.codecool.recipeme.model.Favourite;
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

    @Autowired
    IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    FavouriteRepository favouriteRepository;



    public static void main(String[] args) {
        SpringApplication.run(RecipeMeApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            Favourite fav = Favourite.builder()
                    .build();

            favouriteRepository.saveAndFlush(fav);

            Recipe recipe = Recipe.builder()
                    .label("pho")
                    .favourite(fav)
                    .build();

            recipeRepository.saveAndFlush(recipe);


            RecipeMeUser admin = RecipeMeUser.builder()
                    .name("admin")
                    .roles(Arrays.asList("ADMIN"))
                    .password(passwordEncoder.encode("admin"))
                    .favourites(Arrays.asList(recipe))
                    .build();

            recipeMeUserRepository.saveAndFlush(admin);
        };
    }
}