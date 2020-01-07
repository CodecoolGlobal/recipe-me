package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.RecipeMeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeMeUserRepository extends JpaRepository<RecipeMeUser, Long> {

    Optional<RecipeMeUser> findByName(String name);
}
