package com.codecool.recipeme.security;


import com.codecool.recipeme.model.RecipeMeUser;
import org.springframework.security.core.userdetails.User;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private RecipeMeUserRepository users;

    public CustomUserDetailsService(RecipeMeUserRepository users) {
        this.users = users;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RecipeMeUser recipeMeUser = users.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(recipeMeUser.getName(), recipeMeUser.getPassword(),
                recipeMeUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}