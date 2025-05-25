package com.javarush.island.abdulkhanov.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.abdulkhanov.creator.CreatorOfEntity;
import com.javarush.island.abdulkhanov.entity.ability.Eating;
import com.javarush.island.abdulkhanov.entity.ability.Moveable;
import com.javarush.island.abdulkhanov.entity.ability.Reproducible;
import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;
import com.javarush.island.abdulkhanov.util.EntityCounter;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;

public abstract class Entity implements Reproducible, Moveable, Eating {
    private double weight;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private static final ObjectMapper mapper = new YAMLMapper();
    private final String icon = "";
    private boolean gender;
    private Limit entityLimit;
    private CreatorOfEntity entityCreator = new CreatorOfEntity();

    public Entity() {
    }

    public Entity(double weight, boolean gender, Limit entityLimit) {
        this.weight = weight;
        this.gender = gender;
        this.entityLimit = entityLimit;
    }

    public boolean move(Cell cell){
        int maxCount = Integer.parseInt(this.getEntityLimit().getMaxCountInCell());
        int countOfMoves = Randomiser.getRandomCount(1, Integer.parseInt(this.getEntityLimit().getMaxSpeed()));
        Cell nextCell = safeGetNextCell(cell);
        --countOfMoves;
        for(int i = 0; i<countOfMoves-1; i++){
            nextCell = safeGetNextCell(nextCell);
        }
        if(nextCell==null){
            return false;
        }
        safeAddBornEntityToDefinedDeque(nextCell, maxCount);
        return true;
    }

    private Cell safeGetNextCell(Cell cell) {
        synchronized (cell.monitor()) {
            List<Cell> listOfNeighbours = cell.getNextCells();
            return defineVariant(listOfNeighbours, cell);
        }
    }

    private Cell defineVariant(List<Cell> listOfNeighbours, Cell currentCell) {
        int maxCountInCell = Integer.parseInt(this.getEntityLimit().getMaxCountInCell());
        List<Cell> notVisitedCells = listOfNeighbours.stream().filter(o -> !o.equals(currentCell)).toList();
        for (Cell notVisitedCell : notVisitedCells) {
            Cell vacantCell = safeCheckAndReturnCell(notVisitedCell, maxCountInCell);
            if (vacantCell != null) return vacantCell;
        }
        return null;
    }

    private Cell safeCheckAndReturnCell(Cell notVisitedCell, int maxCountInCell) {
        synchronized (notVisitedCell.monitor()) {
            ArrayDeque<Entity> entityDeque = notVisitedCell.getResidentsInCell().values().stream()
                    .filter(o -> o.getFirst().getClass() != this.getClass())
                    .findAny().get();
            if (entityDeque.size() < maxCountInCell) {
                return notVisitedCell;
            }
            return null;
        }
    }

    @Override
    public boolean reproduce(Cell cell){
        Class<? extends Entity> someResidentClass = safeFindPotentialParents(cell);
        if(someResidentClass==null){
            return false;
        }
        Entity someEntity = entityCreator.create(someResidentClass);
        int maxCountInCellEntityLimit = Integer.parseInt(someEntity.getEntityLimit().getMaxCountInCell());
        if(safeAddBornEntityToDefinedDeque(cell, maxCountInCellEntityLimit)){
            return true;
        }
        if(!someEntity.move(cell)){
            EntityCounter.reducePopulation(getSomeTypeOfEntity());
        }
        return false;
    }

    private TypeOfEntity getSomeTypeOfEntity() {
        return TypeOfEntity.valueOf(this.getClass().getName().toUpperCase());
    }


    private boolean safeAddBornEntityToDefinedDeque(Cell cell, int maxCountInCellEntityLimit) {
        synchronized (cell.monitor()) {
            TypeOfEntity definedType = getSomeTypeOfEntity();
            ArrayDeque<Entity> definedDeque = cell.getResidentsInCell().get(definedType);
            if (definedDeque.size() < maxCountInCellEntityLimit) {
                definedDeque.add(this);
                return true;
            } else {
                return false;
            }
        }
    }

    private Class<? extends Entity> safeFindPotentialParents(Cell cell) {
        synchronized (cell.monitor()){
            ArrayDeque<Entity> someResidentDeque = getSomeResidentDeque(cell);
            if(someResidentDeque.isEmpty()){
                return null;
            }
            Entity someEntityWithSomeGender = someResidentDeque.getFirst();
            Entity otherEntityWithOtherGender = someResidentDeque.stream()
                    .filter(o -> o.isGender()!=someEntityWithSomeGender.isGender())
                    .findFirst().orElseGet(null);
            return someEntityWithSomeGender.getClass();
        }
    }

    private static ArrayDeque<Entity> getSomeResidentDeque(Cell cell) {
        ArrayDeque<Entity> someResidentDeque =  cell.getResidentsInCell()
                .values()
                .stream()
                .filter(Objects::nonNull)
                .findAny().orElseGet(null);
        return someResidentDeque;
    }

    public boolean isGender() {
        return gender;
    }

    public Limit getEntityLimit() {
        return entityLimit;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public Limit readConfig(){
        String statsPath = this.getStatsPath();
        File file = new File(statsPath);
        try {
            this.entityLimit = getMapper().readValue(file, Limit.class);
            return this.entityLimit;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract String getStatsPath();

    public double getWeight() {
        return weight;
    }

    public String getIcon(){
        return icon;
    }

    public abstract boolean eat(Cell cell);
}
