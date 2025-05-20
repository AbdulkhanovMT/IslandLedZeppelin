package com.javarush.island.abdulkhanov.entity.plant;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.ability.Reproducible;

public class Plant extends Entity implements Reproducible {
    private final double weight = 1.0;
    public static final int MAX_IN_CELL = 200;
    public static final int MIN_IN_CELL = MAX_IN_CELL/10;

    public int getMaxInCell() {
        return MAX_IN_CELL;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean reproduce(Entity entity) {
        return false;
    }
}
