package com.javarush.island.abdulkhanov.gamefield;

import com.javarush.island.abdulkhanov.creator.CreatorOfCell;

public class IslandTerritory {
    private final int ROWS = 3;
    private final int COLUMNS = 5;
    private final Cell[][] cells = new Cell[ROWS][COLUMNS];
    private final CreatorOfCell cellCreator = new CreatorOfCell();

    public IslandTerritory() {

    }

    public void initializeSimulationMap(){
        createCells();
        writeNeighboursCells();
    }


    private void createCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[ROWS].length; j++) {
                cells[i][j] = cellCreator.create(Cell.class);
            }
        }
    }

    private void writeNeighboursCells() {
        for (Cell[] cell : cells) {
            for (int i = 0; i < cells[ROWS].length; i++) {
                cell[i].findNextCells(cells);
            }
        }
    }
}
