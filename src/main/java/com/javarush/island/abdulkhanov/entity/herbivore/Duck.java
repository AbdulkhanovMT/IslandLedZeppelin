package com.javarush.island.abdulkhanov.entity.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Duck extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/duck_limit.yaml";
    private final String icon = "\uD83D\uDC07";

    public Duck() {
    }

    public Duck(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }

    public String getStatsPath() {
        return statsPath;
    }

}
