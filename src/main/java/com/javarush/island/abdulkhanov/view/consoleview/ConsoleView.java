package com.javarush.island.abdulkhanov.view.consoleview;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.gamefield.Cell;
import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.view.View;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConsoleView implements View {

    private final int charSequenceLength = 4;
    private final int rowsLimit = 5;
    private final int colsLimit = 10;

    private final String LEFT_TOP = "┌";
    private final String RIGHT_TOP = "┐";
    private final String TOP_CELL_DELIMITER = "┬";
    private final String RIGHT_BOTTOM = "┘";
    private final String LEFT_BOTTOM = "└";
    private final String BOTTOM_CELL_DELIMITER = "┴";
    private final String LEFT_CELL_DELIMITER = "├";
    private final String RIGHT_CELL_DELIMITER = "┤";
    private final String INNER_CELL_DELIMITER = "┼";
    private final String HORIZONTAL = "─";
    private final String VERTICAL = "│";
    private final String LINE_BREAKER = "\n";

    public ConsoleView() {
    }

    public void drawMap(IslandTerritory island) {
        StringBuilder builder = new StringBuilder();
        Cell[][] cells = island.getCells();
        int limit = Math.min(rowsLimit, cells.length);
        for (int i = 0; i < limit - 1; i++) {
            builder.append(drawTop(i, cells[i].length, cells.length));
            builder.append(drawLine(cells[i], VERTICAL));
        }
        builder.append(drawBottom(cells[0].length));
        System.out.println(builder);
    }

    private String drawBottom(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append(LEFT_BOTTOM);
        for (int i = 0; i < length; i++) {
            builder.append(HORIZONTAL.repeat(charSequenceLength + 1));
            if (i == length - 1) {
                builder.append(RIGHT_BOTTOM);
            } else {
                builder.append(BOTTOM_CELL_DELIMITER);
            }
        }
        return builder.toString();
    }

    public String drawLine(Cell[] cellsRow, String verticalBorder) {
        StringBuilder builder = new StringBuilder();
        builder.append(verticalBorder);
        int limit = Math.min(colsLimit, cellsRow.length);
        for (int i = 0; i < limit; i++) {
            Cell cell = cellsRow[i];
            builder.append(drawCell(cell));
        }
        if (cellsRow.length <= colsLimit) {
            builder.append(verticalBorder);
        } else{
            builder.append('#');
        }
        builder.append(LINE_BREAKER);
        return builder.toString();
    }

    public String drawCell(Cell cell) {
        List<ArrayDeque<Entity>> cellEntityList = getTypeDequesAsList(cell);
        char[] literArray = getFirstCharsOfTypes(cellEntityList);
        StringBuilder builder = new StringBuilder();
        builder.append(VERTICAL);
        writeCharsAtCellString(builder, literArray);
        return builder.toString();
    }

    private List<ArrayDeque<Entity>> getTypeDequesAsList(Cell cell) {
        List<ArrayDeque<Entity>> cellEntityList = new ArrayList<>(charSequenceLength);
        for (int i = 0; i < charSequenceLength; i++) {
            cellEntityList.add(null);
        }
        cellEntityList = cell.getResidentsInCell().values()
                .stream()
                .filter(o -> !o.isEmpty())
                .sorted(Comparator.comparingInt(ArrayDeque::size))
                .limit(4)
                .toList();
        return cellEntityList;
    }

    private char[] getFirstCharsOfTypes(List<ArrayDeque<Entity>> cellEntityList) {
        char[] literArray = new char[charSequenceLength];
        for (int i = 0; i < literArray.length; i++) {
            if (cellEntityList.get(i) != null) {
                literArray[i] = cellEntityList.get(i).getFirst().getClass().getName().charAt(0);
            } else {
                literArray[i] = '.';
            }
        }
        return literArray;
    }

    private void writeCharsAtCellString(StringBuilder builder, char[] literArray) {
        for (int i = 0; i < charSequenceLength; i++) {
            builder.append(literArray[i]);
        }
    }


    private String drawTop(int row, int islandLength, int length) {
        StringBuilder builder = new StringBuilder();
        if (row == 0) {
            builder.append(LEFT_TOP);
        } else {
            builder.append(LEFT_CELL_DELIMITER);
        }
        builder.append(HORIZONTAL.repeat(charSequenceLength));
        for (int i = 0; i < islandLength - 1; i++) {
            if (row > 0 && row < length - 1) {
                builder.append(INNER_CELL_DELIMITER);
            } else {
                builder.append(TOP_CELL_DELIMITER);
            }
            builder.append(HORIZONTAL.repeat(charSequenceLength));
        }
        if (row > 0 && row < length - 1) {
            builder.append(RIGHT_CELL_DELIMITER);
        } else {
            builder.append(RIGHT_TOP);
        }
        builder.append(LINE_BREAKER);
        return builder.toString();
    }

    @Override
    public void show(IslandTerritory islandTerritory) {
        showMap(islandTerritory);
        showStatistics(islandTerritory);
        showScale(islandTerritory);
    }

    @Override
    public void showMap(IslandTerritory islandTerritory) {
        drawMap(islandTerritory);
    }

    @Override
    public void showStatistics(IslandTerritory islandTerritory) {

    }

    @Override
    public void showScale(IslandTerritory islandTerritory) {
        System.out.println("\n");
    }
}
