package com.javarush.island.abdulkhanov.entity.predator;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Eagle extends Predator{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/predator/eagle_limit.yaml";
    private final String icon = "\uD83D\uDC3A";

    public Eagle() {
    }

    public Eagle(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }

    public String getStatsPath() {
        return statsPath;
    }

}
