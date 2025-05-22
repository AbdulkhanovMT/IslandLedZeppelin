package com.javarush.island.abdulkhanov.entity.limit;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"maxSpeed", "maxWeight", "maxCountInCell", "maxFood"})
public class Limit {

    private String maxSpeed;
    private String maxWeight;
    private String maxCountInCell;
    private String maxFood;

    public Limit() {
    }

    public Limit(String maxSpeed, String maxWeight, String maxCountInCell, String maxFood) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
        this.maxCountInCell = maxCountInCell;
        this.maxFood = maxFood;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public String getMaxCountInCell() {
        return maxCountInCell;
    }

    public String getMaxFood() {
        return maxFood;
    }

}
