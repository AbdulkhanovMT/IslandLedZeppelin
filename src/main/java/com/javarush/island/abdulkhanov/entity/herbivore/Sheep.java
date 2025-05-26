package com.javarush.island.abdulkhanov.entity.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Sheep extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/sheep_limit.yaml";
    private final String icon = "\uD83D\uDC11";

    public Sheep() {
    }

    public Sheep(double weight, boolean gender, Limit animalLimit) {
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
