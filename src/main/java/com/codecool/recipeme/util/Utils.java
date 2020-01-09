package com.codecool.recipeme.util;

import com.codecool.recipeme.model.RecipeMeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    public static RecipeMeUser getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (RecipeMeUser) authentication.getPrincipal();
    }
}
