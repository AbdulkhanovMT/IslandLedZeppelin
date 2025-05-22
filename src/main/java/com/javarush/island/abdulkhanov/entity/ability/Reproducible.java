package com.javarush.island.abdulkhanov.entity.ability;

import com.javarush.island.abdulkhanov.gamefield.Cell;

public interface Reproducible {
    boolean reproduce(Cell cell);
}
