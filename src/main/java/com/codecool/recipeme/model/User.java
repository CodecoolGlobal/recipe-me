package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Recipe> favourites;

    @OneToOne
    private ShoppingCart shoppingCart;

}
