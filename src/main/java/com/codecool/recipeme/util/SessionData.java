package com.codecool.recipeme.util;

import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionData {

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    public RecipeMeUser getRecipeMeUserFromCookie() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        RecipeMeUser recipeMeUser = recipeMeUserRepository.findByName(username).get();
        return recipeMeUser;
    }
}
