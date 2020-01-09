package com.codecool.recipeme.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


@Generated("com.robohorse.robopojogenerator")
public class IngredientsItem {
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