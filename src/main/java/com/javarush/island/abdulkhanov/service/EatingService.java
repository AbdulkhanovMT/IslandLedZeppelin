package com.javarush.island.abdulkhanov.service;

import com.javarush.island.abdulkhanov.Simulation;

public class EatingService extends AbstractService {

    public EatingService(Simulation simulation){
        super(simulation);
    }

    @Override
    public void run() {
        simulation.getIsland().getStreamOfCells()
                .forEach(cell -> processOneCell(cell, o -> o.eat(cell)));
    }
}
