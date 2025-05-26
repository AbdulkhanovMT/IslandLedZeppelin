package com.javarush.island.abdulkhanov.view.consoleview;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;
import com.javarush.island.abdulkhanov.gamefield.Cell;
import com.javarush.island.abdulkhanov.gamefield.IslandTerritory;
import com.javarush.island.abdulkhanov.util.EntityCounter;
import com.javarush.island.abdulkhanov.util.EntityMap;
import com.javarush.island.abdulkhanov.view.View;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.*;

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
            builder.append(drawTop(i, cells.length, cells[0].length));
            builder.append(drawLine(cells[i], VERTICAL));
        }
        builder.append(drawBottom(cells[0].length, cells.length));
        System.out.println(builder);
    }

    private String drawBottom(int length, int row) {
        StringBuilder builder = new StringBuilder();
        if (row > rowsLimit) {
            builder.append("#");
        } else {
            builder.append(LEFT_BOTTOM);
        }
        for (int i = row > rowsLimit ? 1 : 0; i < length; i++) {
            if (row > rowsLimit) {
                builder.append("#".repeat(charSequenceLength));
            } else {
                builder.append(HORIZONTAL.repeat(charSequenceLength));
            }
            if (row > rowsLimit) {
                builder.append("#");
            } else {
                if (i == length - 1) {
                    builder.append(RIGHT_BOTTOM);
                } else {
                    builder.append(BOTTOM_CELL_DELIMITER);
                }
            }
        }
        return builder.toString();
    }

    public String drawLine(Cell[] cellsRow, String verticalBorder) {
        StringBuilder builder = new StringBuilder();
        int limit = Math.min(colsLimit, cellsRow.length);
        for (int i = 0; i < limit; i++) {
            Cell cell = cellsRow[i];
            builder.append(drawCell(cell));
        }
        if (cellsRow.length <= colsLimit) {
            builder.append(verticalBorder);
        } else {
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
        for (int i = 0; i < charSequenceLength; i++) {
            if (cellEntityList.isEmpty()||i==cellEntityList.size()) {
                for (int j = 0; j < literArray.length-i; j++) {
                    literArray[i] = '.';
                }
                break;
            }
            if (cellEntityList.get(i) != null) {
                literArray[i] = cellEntityList.get(i).getFirst().getClass().getSimpleName().charAt(0);
            } else {
                literArray[i] = '.';
            }
        }
        return literArray;
    }

    private void writeCharsAtCellString(StringBuilder builder, char[] literArray) {
        for (int i = 0; i < charSequenceLength; i++) {
            if (literArray[i] != 0) {
                builder.append(literArray[i]);
            } else {
                builder.append('.');
            }
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
        for (int i = length > colsLimit ? 1 : 0; i < islandLength - 1; i++) {
            if (row > 0 && row < length - 1) {
                builder.append(INNER_CELL_DELIMITER);
            } else {
                builder.append(TOP_CELL_DELIMITER);
            }
            builder.append(HORIZONTAL.repeat(charSequenceLength));
        }
        if (length > colsLimit) {
            builder.append("#");
        } else {
            if (row > 0 && row < length - 1) {
                builder.append(RIGHT_CELL_DELIMITER);
            } else {
                builder.append(RIGHT_TOP);
            }
        }
        builder.append(LINE_BREAKER);
        return builder.toString();
    }

    @Override
    public void show(IslandTerritory islandTerritory) {
        showMap(islandTerritory);
        showStatistics();
        showScale();
    }

    @Override
    public void showMap(IslandTerritory islandTerritory) {
        drawMap(islandTerritory);
    }

    @Override
    public void showStatistics() {
        List<Map.Entry<TypeOfEntity, Integer>> entities = getCountedEntriesOfEntities();
        StringBuilder builder = new StringBuilder();
        writeStatistics(entities, builder);
        builder.append(LINE_BREAKER);
        System.out.println(builder);
    }

    private static void writeStatistics(List<Map.Entry<TypeOfEntity, Integer>> entities, StringBuilder builder) {
        for (Map.Entry<TypeOfEntity, Integer> entity : entities) {
            try {
                Class<Entity> entityClass = (Class<Entity>) EntityMap.ENTITIES.get(entity.getKey());
                Entity entityInstance = entityClass.getConstructor().newInstance();
                builder.append(entityInstance.getIcon()).append(": ").append(entity.getValue()).append("\t");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void showScale() {
        List<Map.Entry<TypeOfEntity, Integer>> entities = getCountedEntriesOfEntities();
        StringBuilder builder = new StringBuilder();
        int sum = EntityCounter.getSumOfRecords();
        for (Map.Entry<TypeOfEntity, Integer> entity : entities) {
            if (sum != 0) {
                Class<Entity> entityClass = (Class<Entity>) EntityMap.ENTITIES.get(entity.getKey());
                try {
                    Entity entityInstance = entityClass.getConstructor().newInstance();
                    builder.append(entityInstance.getIcon())
                            .append(": ")
                            .append(getFormattedDouble(1.0 * entity.getValue() * 100 / sum))
                            .append("%\t");
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        builder.append(LINE_BREAKER + LINE_BREAKER);
        System.out.println(builder);
    }

    private static List<Map.Entry<TypeOfEntity, Integer>> getCountedEntriesOfEntities() {
        return EntityCounter.getEntitiesRecord().entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();
    }

    private String getFormattedDouble(double number) {
        return new DecimalFormat("#0.00").format(number);
    }
}
