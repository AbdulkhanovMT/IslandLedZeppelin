package com.javarush.island.abdulkhanov.entity.predator;

import com.javarush.island.abdulkhanov.entity.limit.Limit;
import lombok.Getter;

public class Eagle extends Predator{

    private final String statsPath = "src/main/java/com/javarush/island/abdulkhanov/config/predator/eagle_limit.yaml";
    private final String icon = "\uD83E\uDD85";

    public Eagle() {
    }

    public Eagle(double weight, boolean gender, Limit animalLimit) {
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
