package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;

import java.lang.reflect.InvocationTargetException;

public class CreatorOfTerritory extends Creator<IslandTerritory> {

    @Override
    public IslandTerritory create(Class<? extends IslandTerritory> tClass) {
        try {
            return tClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}

