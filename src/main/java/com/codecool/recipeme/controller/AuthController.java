package com.codecool.recipeme.controller;


import com.codecool.recipeme.model.UserCredentials;
import com.codecool.recipeme.repository.UserRepository;
import com.codecool.recipeme.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth/")
public class AuthController {

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserRepository users) {

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

     @Autowired
    UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public void registration(@RequestBody UserCredentials user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getUsername() + " " + user.getPassword());
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody UserCredentials data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}
