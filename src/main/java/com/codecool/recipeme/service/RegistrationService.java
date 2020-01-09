package com.codecool.recipeme.service;

import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.model.ShoppingCart;
import com.codecool.recipeme.model.UserCredentials;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RegistrationService {

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public void registerNewRecipeMeUser(UserCredentials user) {

        ShoppingCart shoppingCart = new ShoppingCart();

        RecipeMeUser recipeMeUser = RecipeMeUser.builder()
                .name(user.getUsername())
                .password(user.getPassword())
                .roles(Arrays.asList("ROLE_USER"))
                .shoppingCart(shoppingCart)
                .build();

        recipeMeUserRepository.saveAndFlush(recipeMeUser);
    }
}
