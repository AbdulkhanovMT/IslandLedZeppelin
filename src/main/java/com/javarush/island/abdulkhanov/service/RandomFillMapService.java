package com.javarush.island.abdulkhanov.service;

import com.javarush.island.abdulkhanov.Simulation;
import com.javarush.island.abdulkhanov.gamefield.Cell;
import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.util.Randomiser;

public class RandomFillMapService extends AbstractService {

    private final int ROWS;
    private final int COLS;

    public RandomFillMapService(Simulation simulation){
        super(simulation);
        ROWS = IslandTerritory.getROWS();;
        COLS = IslandTerritory.getCOLUMNS();
    }

    @Override
    public void run() {
        int row = Randomiser.getRandomCount(0, ROWS);
        int col = Randomiser.getRandomCount(0, COLS);
        Cell randomCell = simulation.getIsland().getCell(row, col);
        safeFill(randomCell);
    }

    private void safeFill(Cell cell){
        synchronized (cell.monitor()){
            cell.addResidentsToCell();
        }
    }
}
