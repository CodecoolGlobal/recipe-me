package com.codecool.recipeme.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Generated("com.robohorse.robopojogenerator")
public class IngredientsItem {

    @Id
    @GeneratedValue
    long id;

    @ManyToOne()
    Recipe recipe;

    @JsonProperty("weight")
    private double weight;

    @JsonProperty("text")
    private String text;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return
                "IngredientsItem{" +
                        "weight = '" + weight + '\'' +
                        ",text = '" + text + '\'' +
                        "}";
    }
}