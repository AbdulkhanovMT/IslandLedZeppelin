package com.javarush.island.abdulkhanov.gamefield;

import com.javarush.island.abdulkhanov.creator.CreatorOfEntity;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.util.EntityMap;
import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Cell {

    private final ConcurrentHashMap<TypeOfEntity, ArrayDeque<Entity>> residentsInCell = new ConcurrentHashMap<>();
    private final CreatorOfEntity entityCreator = new CreatorOfEntity();
    private final List<Cell> nextCells = new ArrayList<>();
    private static int idCounter = 0;
    private final int id;

    public Cell() {
        id = idCounter++;
        this.addResidentsToCell();
    }

    public ConcurrentHashMap<TypeOfEntity, ArrayDeque<Entity>> getResidentsInCell() {
        return residentsInCell;
    }

    public List<Cell> getNextCells() {
        return nextCells;
    }

    public void findNextCells(Cell[][] cellsOfTerritory) {
        int[] definedRowAndCol = this.defineRowAndCol(cellsOfTerritory);
        int row = definedRowAndCol != null ? definedRowAndCol[0] : 0;
        int col = definedRowAndCol != null ? definedRowAndCol[1] : 0;
        int higher = Math.max((row - 1), 0);
        int lower = ((row + 1) > (cellsOfTerritory.length - 1)) ? row : (row + 1);
        int rightSide = ((col + 1) > (cellsOfTerritory[row].length - 1)) ? col : (col + 1);
        int leftSide = Math.max((col - 1), 0);
        addCellsNeighbours(cellsOfTerritory, lower, col, row, rightSide, leftSide, higher);
    }

    private void addCellsNeighbours(Cell[][] cellsOfTerritory, int lower, int col, int row, int rightSide, int leftSide, int higher) {
        nextCells.add(cellsOfTerritory[lower][col]);
        nextCells.add(cellsOfTerritory[row][rightSide]);
        nextCells.add(cellsOfTerritory[row][leftSide]);
        nextCells.add(cellsOfTerritory[higher][col]);
    }

    private int[] defineRowAndCol(Cell[][] cellsOfTerritory) {
        for (int i = 0; i < cellsOfTerritory.length; i++) {
            for (int j = 0; j < cellsOfTerritory[0].length; j++) {
                if(this.equals(cellsOfTerritory[i][j])){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void addResidentsToCell() {
        List<TypeOfEntity> types = Arrays.stream(TypeOfEntity.values()).collect(Collectors.toList());
        int randomCountOfCycles = defineCountOfTypesOfEntityInCell(types);
        for (int i = 0; i < randomCountOfCycles; i++) {
            TypeOfEntity randomType = types.get(Randomiser.getRandomCount(0, types.size()));
            @SuppressWarnings("unchecked")
            Class<? extends Entity> randomClass = (Class<? extends Entity>) EntityMap.ENTITIES.get(randomType);
            try {
                Limit randomLimit = randomClass.getConstructor().newInstance().readConfig();
                int randomMaxCountInCell = Integer.parseInt(randomLimit.getMaxCountInCell());
                int randomCount = Randomiser.getRandomCount(0, randomMaxCountInCell);
                if (residentsInCell.containsKey(randomType)) {
                    ArrayDeque<Entity> residentsDeque = residentsInCell.get(randomType);
                    int countInCell = residentsDeque.size();
                    if (randomMaxCountInCell == countInCell) continue;
                    if (countInCell < randomMaxCountInCell) {
                        int vacantPlaces = randomMaxCountInCell - countInCell;
                        int freePlaces = Math.min(randomCount, vacantPlaces);
                        mergeDeque(freePlaces, randomClass, residentsDeque);
                    }
                } else {
                    ArrayDeque<Entity> randomAnimalDeque = fillEntityDeque(randomCount, randomClass);
                    residentsInCell.put(randomType, randomAnimalDeque);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void mergeDeque(int freePlaces, Class<? extends Entity> randomClass, ArrayDeque<Entity> residentsDeque) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (int j = 0; j < freePlaces; j++) {
            Entity randomEntity = entityCreator.create(randomClass);
            residentsDeque.add(randomEntity);
        }
    }

    private static int defineCountOfTypesOfEntityInCell(List<TypeOfEntity> types) {
        return Randomiser.getRandomCount(1, types.size() + 1);
    }

    private ArrayDeque<Entity> fillEntityDeque(int randomCount, Class<? extends Entity> randomClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ArrayDeque<Entity> randomAnimalDeque = new ArrayDeque<>();
        for (int j = 0; j < randomCount; j++) {
            Entity randomEntity = entityCreator.create(randomClass);
            randomAnimalDeque.add(randomEntity);
        }
        return randomAnimalDeque;
    }

    public Object monitor() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return id == cell.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
