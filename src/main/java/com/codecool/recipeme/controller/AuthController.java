package com.codecool.recipeme.controller;


import com.codecool.recipeme.model.UserCredentials;
import com.codecool.recipeme.repository.RecipeMeUserRepository;
import com.codecool.recipeme.security.JwtTokenServices;
import com.codecool.recipeme.service.RegistrationService;
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
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth/")
public class AuthController {

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, RecipeMeUserRepository users) {

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

     @Autowired
     RecipeMeUserRepository recipeMeUserRepository;

    @Autowired
    RegistrationService registrationService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public void registration(@RequestBody UserCredentials user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        registrationService.registerNewRecipeMeUser(user);
    }

    @GetMapping("/logout")
    public static void logOut(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
    }



    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(cookie.getMaxAge());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}
