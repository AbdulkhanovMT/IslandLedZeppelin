package com.javarush.island.abdulkhanov.gamefield;

import com.javarush.island.abdulkhanov.creator.CreatorOfCell;

import java.util.Arrays;
import java.util.stream.Stream;

public class IslandTerritory {
    private static final int ROWS = 11;
    private static final int COLUMNS = 11;
    private final Cell[][] cells = new Cell[ROWS][COLUMNS];


    private final CreatorOfCell cellCreator = new CreatorOfCell();

    public IslandTerritory() {
        this.initializeSimulationMap();
    }

    public void initializeSimulationMap(){
        createCells();
        writeNeighboursCells();
    }


    private void createCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[ROWS-1].length; j++) {
                cells[i][j] = cellCreator.create(Cell.class);
            }
        }
    }

    private void writeNeighboursCells() {
        for (Cell[] cell : cells) {
            for (int i = 0; i < cells[ROWS-1].length; i++) {
                cell[i].findNextCells(cells);
            }
        }
    }

    public Stream<Cell> getStreamOfCells(){
        return Arrays.stream(cells).flatMap(Arrays::stream);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public static int getROWS() {
        return ROWS;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
