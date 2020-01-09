package com.codecool.recipeme.model;

import com.codecool.recipeme.model.generated.IngredientsItem;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RMIngredientsItem {

    public RMIngredientsItem(IngredientsItem ingredientsItem) {
        weight = ingredientsItem.getWeight();
        text = ingredientsItem.getText();
    }

    @Id
    @GeneratedValue
    long id;

    private double weight;

    private String text;
}
