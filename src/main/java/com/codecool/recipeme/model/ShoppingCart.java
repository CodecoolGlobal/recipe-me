package com.codecool.recipeme.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue
    Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private Set<RMIngredientsItem> ingredients;

    public void addIngredient(RMIngredientsItem rmIngredientsItem){
        if (ingredients == null) {
            ingredients = new HashSet<>();
        }
        ingredients.add(rmIngredientsItem);
    }

}
