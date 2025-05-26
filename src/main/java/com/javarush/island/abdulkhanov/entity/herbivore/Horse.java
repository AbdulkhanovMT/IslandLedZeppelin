package com.javarush.island.abdulkhanov.entity.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Horse extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/horse_limit.yaml";
    private final String icon = "\uD83D\uDC0E";

    public Horse() {
    }

    public Horse(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }

    public String getStatsPath() {
        return statsPath;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
