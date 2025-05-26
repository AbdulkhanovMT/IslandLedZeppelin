package com.javarush.island.abdulkhanov;

import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.service.*;
import com.javarush.island.abdulkhanov.view.View;
import com.javarush.island.abdulkhanov.view.consoleview.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRunner {
    public static void main(String[] args) {
        IslandTerritory island = new IslandTerritory();
        View consoleView = new ConsoleView();
        Simulation simulation = new Simulation(island, consoleView);
        List<Runnable> services = new ArrayList<>();
        services.add(new EatingService(simulation));
        services.add(new MovingService(simulation));
        services.add(new ReproducingService(simulation));
        services.add(new RandomFillMapService(simulation));
        services.add(new ViewRenderService(simulation));
        SimulationProcessor simulationProcessor = new SimulationProcessor(simulation, services);
        simulationProcessor.start();
    }
}
