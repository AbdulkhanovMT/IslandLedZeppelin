package com.javarush.island.abdulkhanov.entity.predator;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Fox extends Predator{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/predator/fox_limit.yaml";
    private final String icon = "\uD83E\uDD8A";

    public Fox() {
    }

    public Fox(double weight, boolean gender, Limit animalLimit) {
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
