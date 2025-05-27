package com.javarush.island.abdulkhanov.entity.animal.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Goat extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/goat_limit.yaml";
    private final String icon = "\uD83D\uDC10";

    public Goat() {
    }

    public Goat(double weight, boolean gender, Limit animalLimit) {
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
