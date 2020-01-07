package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.IngredientsItem;
import com.codecool.recipeme.model.generated.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue
    Long id;

    private String name;

    @OneToOne(mappedBy = "shoppingCart")
    private RecipeMeUser recipeMeUser;

    @OneToMany
    @Singular
    private List<IngredientsItem> ingredients;

    @OneToMany
    List<Recipe> recipes;

}
