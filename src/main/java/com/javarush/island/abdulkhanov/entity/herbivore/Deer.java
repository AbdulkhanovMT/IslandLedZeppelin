package com.javarush.island.abdulkhanov.entity.herbivore;

import com.javarush.island.abdulkhanov.entity.limit.Limit;

public class Deer extends Herbivore{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/herbivore/deer_limit.yaml";
    private final String icon = "\uD83E\uDD8C";

    public Deer() {
    }

    public Deer(double weight, boolean gender, Limit animalLimit) {
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
