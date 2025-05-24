package com.javarush.island.abdulkhanov;

import com.javarush.island.abdulkhanov.creator.CreatorOfTerritory;
import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.settings.AnimalSettings;

public class Simulation {
    private final CreatorOfTerritory territoryCreator = new CreatorOfTerritory();
    public static AnimalSettings SETTINGS = new AnimalSettings();

    static
    {
        SETTINGS.loadFoodMap();
    }
}
