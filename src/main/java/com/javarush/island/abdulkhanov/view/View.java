package com.javarush.island.abdulkhanov.view;

import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;

public interface View {

    void show(IslandTerritory islandTerritory);

    void showMap(IslandTerritory islandTerritory);

    void showStatistics(IslandTerritory islandTerritory);

    void showScale(IslandTerritory islandTerritory);
}
