package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.Recipe;
import lombok.*;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RMRecipe {

    public RMRecipe(Recipe recipe) {
        image = recipe.getImage();
        label = recipe.getLabel();
        url = recipe.getUrl();
        ingredients = recipe.getIngredients().stream()
                .map(RMIngredientsItem::new)
                .collect(Collectors.toSet());
    }

    @Id
    @GeneratedValue
    private Long id;

    private String image;

    private String label;

    private String url;

    @Singular
    @OneToMany(cascade = CascadeType.ALL)
    private Set<RMIngredientsItem> ingredients = new HashSet<>();

}
