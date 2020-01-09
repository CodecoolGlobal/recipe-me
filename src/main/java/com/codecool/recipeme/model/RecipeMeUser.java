package com.codecool.recipeme.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RecipeMeUser {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Singular
    private Set<RMRecipe> favourites = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @ElementCollection
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void addFavouriteRecipe(RMRecipe recipe) {
        favourites.add(recipe);
    }

}
