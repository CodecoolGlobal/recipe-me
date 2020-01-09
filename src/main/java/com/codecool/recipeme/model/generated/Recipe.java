package com.codecool.recipeme.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @JsonProperty("image")
    private String image;

    @JsonProperty("shareAs")
    private String shareAs;

    @JsonProperty("cautions")
    private List<String> cautions;

    @JsonProperty("healthLabels")
    private List<String> healthLabels;

    @JsonProperty("totalTime")
    private double totalTime;

    @JsonProperty("label")
    private String label;

    @JsonProperty("source")
    private String source;

    @JsonProperty("calories")
    private double calories;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("url")
    private String url;

    @JsonProperty("totalNutrients")
    private TotalNutrients totalNutrients;

    @JsonProperty("dietLabels")
    private List<String> dietLabels;

    @JsonProperty("yield")
    private double yield;

    @JsonProperty("totalWeight")
    private double totalWeight;

    @JsonProperty("digest")
    private List<DigestItem> digest;

    @JsonProperty("ingredients")
    private List<IngredientsItem> ingredients;

    @JsonProperty("ingredientLines")
    private List<String> ingredientLines;
}