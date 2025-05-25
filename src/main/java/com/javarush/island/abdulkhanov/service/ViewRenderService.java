package com.javarush.island.abdulkhanov.service;

import com.javarush.island.abdulkhanov.Simulation;

public class ViewRenderService extends AbstractService {

    public ViewRenderService(Simulation simulation){
        super(simulation);
    }

    @Override
    public void run() {
        try {
            simulation.getView().show(simulation.getIsland());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
