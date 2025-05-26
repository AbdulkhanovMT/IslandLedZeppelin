package com.javarush.island.abdulkhanov.entity.plant;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;

public class Plant extends Entity {
    private final double weight = 1.0;
    private final String icon = "\uD83C\uDF31";
    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/plants/plant_limit.yaml";

    public Plant() {
    }

    public Plant(double weight, boolean gender, Limit entityLimit) {
        super(weight, gender, entityLimit);
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getStatsPath() {
        return statsPath;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean reproduce(Cell cell) {
        return false;
    }

    @Override
    public boolean move(Cell cell) {
        return false;
    }

    @Override
    public boolean eat(Cell cell) {
        return false;
    }
}
