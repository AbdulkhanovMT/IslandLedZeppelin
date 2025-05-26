package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.gamefield.Cell;

public class CreatorOfCell extends Creator<Cell> {

    @Override
    public Cell create(Class<? extends Cell> tClass) {
        return new Cell();
    }

}

