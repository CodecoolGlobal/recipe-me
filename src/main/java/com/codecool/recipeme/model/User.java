package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    @ManyToMany
    @Singular
    private List<Recipe> favourites;

    @OneToOne
    private ShoppingCart shoppingCart;

}
