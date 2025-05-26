package com.javarush.island.abdulkhanov;

import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.view.View;

public class Simulation {

    private final IslandTerritory island;
    private final View view;
    private final boolean isFinished = false;

    public Simulation(IslandTerritory island, View view){
        this.island = island;
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public IslandTerritory getIsland() {
        return island;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
