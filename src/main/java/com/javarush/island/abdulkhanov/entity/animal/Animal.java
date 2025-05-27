package com.javarush.island.abdulkhanov.entity.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;
import com.javarush.island.abdulkhanov.settings.AnimalSettings;
import com.javarush.island.abdulkhanov.util.EntityCounter;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.util.*;

public abstract class Animal extends Entity {

    public Animal() {
    }

    public Animal(double weight, boolean gender, Limit entityLimit) {
        super(weight, gender, entityLimit);
    }

    public abstract String getStatsPath();

    @Override
    public boolean eat(Cell cell) {
        boolean foundFood = safeFindBestTarget(cell);
        if (!foundFood) {
            this.looseWeight();
            if (this.getWeight() <= 0.0) {
                if (this.safeDie(cell)) {
                    return false;
                }
            }
            this.move(cell);
            return false;
        }
        return true;
    }

    private boolean safeDie(Cell cell) {
        synchronized (cell.monitor()) {
            ArrayDeque<Entity> residentDeque = cell.getResidentsInCell().get(TypeOfEntity.valueOf(this.getClass().getName().toUpperCase()));
            if (!residentDeque.isEmpty()) {
                residentDeque.removeFirst();
                EntityCounter.reducePopulation(TypeOfEntity.valueOf(this.getClass().getName().toUpperCase()));
                return true;
            }
            return false;
        }
    }

    private void looseWeight() {
        this.setWeight(this.getWeight() - 5.0);
    }

    private boolean safeFindBestTarget(Cell cell) {
        synchronized (cell.monitor()) {
            List<ArrayDeque<Entity>> entityDequesList = cell.getResidentsInCell().values().stream().toList();
            Map<String, Integer> targetsMap = AnimalSettings.getTargetMap(this.getClass());
            Set<Map.Entry<String, Integer>> targetsSet = targetsMap.entrySet();
            List<Map.Entry<String, Integer>> orderedListOfTargets = targetsSet.stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue()).toList();
            String targetName = "";
            int percentage = 0;
            for (Map.Entry<String, Integer> orderedListOfTarget : orderedListOfTargets) {
                for (ArrayDeque<Entity> entities : entityDequesList) {
                    if (entities.getFirst().getClass().getSimpleName().equalsIgnoreCase(orderedListOfTarget.getKey())) {
                        targetName = orderedListOfTarget.getKey();
                        percentage = orderedListOfTarget.getValue();
                        break;
                    }
                }
                if (!targetName.equals("") && percentage != 0) {
                    int randomChance = Randomiser.getRandomCount(0, 101);
                    if (randomChance < percentage) {
                        removeTarget(cell, targetName);
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
    }

    private void removeTarget(Cell cell, String targetName) {
        ArrayDeque<Entity> entityDeque = cell.getResidentsInCell().get(TypeOfEntity.valueOf(targetName.toUpperCase()));
        Entity eatenEntity = entityDeque.poll();
        EntityCounter.reducePopulation(TypeOfEntity.valueOf(eatenEntity.getClass().getSimpleName().toUpperCase()));
        this.addWeight(eatenEntity.getWeight());
    }

    private void addWeight(double eatenWeight) {
        double maxWeight = Double.parseDouble(this.getEntityLimit().getMaxWeight());
        if (Math.abs(maxWeight - this.getWeight() - eatenWeight) < 0.0001) {
            this.setWeight(maxWeight);
        } else {
            this.setWeight(Math.min(maxWeight, this.getWeight() + eatenWeight));
        }
    }

    public ObjectMapper getMapper() {
        return super.getMapper();
    }

    @Override
    public String toString() {
        return "Animal: " + this.getClass().getName();
    }

}
