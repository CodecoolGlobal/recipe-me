package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @Singular
    private List<Recipe> favourites;

    @OneToOne(mappedBy = "recipeMeUser", cascade = CascadeType.PERSIST)
    private ShoppingCart shoppingCart;

    @ElementCollection
    @Builder.Default
    private List<String> roles = new ArrayList<>();

}
