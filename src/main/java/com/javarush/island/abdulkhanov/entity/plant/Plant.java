package com.javarush.island.abdulkhanov.entity.plant;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.gamefield.Cell;

public class Plant extends Entity {
    private final double weight = 1.0;
    private final String icon = "";
    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/plants/plant_limit.yaml";

    @Override
    public String getStatsPath() {
        return "";
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
}
