package com.javarush.island.abdulkhanov.service;

import com.javarush.island.abdulkhanov.Simulation;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.gamefield.Cell;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractService implements Runnable{

    protected final Simulation simulation;

    protected AbstractService(Simulation simulation) {
        this.simulation = simulation;
    }

    protected void processOneCell(Cell cell, Consumer<Entity> action) {
        var all = safeReadAll(cell);
        all.forEach(action);
    }

    private Set<Entity> safeReadAll(Cell cell) {
        synchronized (cell.monitor()){
            return cell.getResidentsInCell().values().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
    }

}
