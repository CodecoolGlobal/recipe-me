package com.codecool.recipeme.repository;

import com.codecool.recipeme.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}
