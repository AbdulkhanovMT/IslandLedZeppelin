package com.javarush.island.abdulkhanov.entity.animal.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Buffalo extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/buffalo_limit.yaml";
    private final String icon = "\uD83D\uDC03";

    public Buffalo() {
    }

    public Buffalo(double weight, boolean gender, Limit animalLimit) {
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
