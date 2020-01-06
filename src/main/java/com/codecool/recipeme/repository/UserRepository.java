package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
