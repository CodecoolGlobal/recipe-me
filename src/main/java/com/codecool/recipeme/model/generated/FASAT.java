package com.codecool.recipeme.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class FASAT {

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private double quantity;

    @JsonProperty("label")
    private String label;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return
                "FASAT{" +
                        "unit = '" + unit + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",label = '" + label + '\'' +
                        "}";
    }
}