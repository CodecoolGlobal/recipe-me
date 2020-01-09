package com.codecool.recipeme.util;

import com.codecool.recipeme.model.RecipeMeUser;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Utils {

    @Autowired
    RecipeMeUserRepository recipeMeUserRepository;

    public RecipeMeUser getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        Optional<RecipeMeUser> recipeMeUser = recipeMeUserRepository.findByName(username);
        if (recipeMeUser.isEmpty()) {
            return null;
        }
        return recipeMeUser.get();
    }
}
