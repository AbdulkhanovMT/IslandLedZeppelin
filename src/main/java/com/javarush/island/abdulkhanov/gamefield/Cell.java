package com.javarush.island.abdulkhanov.gamefield;

import com.javarush.island.abdulkhanov.creator.CreatorOfAnimal;
import com.javarush.island.abdulkhanov.creator.CreatorOfPlant;
import com.javarush.island.abdulkhanov.entity.animal.Animal;
import com.javarush.island.abdulkhanov.entity.animal.AnimalMap;
import com.javarush.island.abdulkhanov.entity.animal.TypeOfAnimal;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.entity.plant.Plant;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Cell {
    private HashMap<TypeOfAnimal, ArrayDeque<Animal>> residentsInCell = new HashMap<>();
    private ArrayList<Plant> listOfPlants = new ArrayList<>();
    private CreatorOfPlant plantCreator = new CreatorOfPlant();
    private CreatorOfAnimal animalCreator = new CreatorOfAnimal();

    public HashMap<TypeOfAnimal, ArrayDeque<Animal>> getResidentsInCell() {
        return residentsInCell;
    }

    public ArrayList<Plant> getListOfPlants() {
        return listOfPlants;
    }

    public void addPlantsInCell() {
        int plantCount = Randomiser.getRandomCount(Plant.MIN_IN_CELL, Plant.MAX_IN_CELL);
        Class<? extends Plant> plantClass = Plant.class;
        for (int i = 0; i < plantCount; i++) {
            listOfPlants.add(plantCreator.create(plantClass));
        }
    }

    public void addAnimalsInCell(){
        List<TypeOfAnimal> types = Arrays.stream(TypeOfAnimal.values()).collect(Collectors.toList());
        int randomCountOfCycles = defineCountOfTypesOfAnimalInCell(types);
        int randomMaxCountInCell = 0;
        for (int i = 0; i < randomCountOfCycles; i++) {
            TypeOfAnimal randomType = types.get(Randomiser.getRandomCount(0, types.size()));
            Class<? extends Animal> randomClass = (Class<? extends Animal>) AnimalMap.ANIMALS.get(randomType);
            try {
                Limit randomLimit = randomClass.getConstructor().newInstance().readConfig();
                randomMaxCountInCell = Integer.parseInt(randomLimit.getMaxCountInCell());
                int randomCount = Randomiser.getRandomCount(0, randomMaxCountInCell);
                if(residentsInCell.containsKey(randomType)){
                    ArrayDeque<Animal> residentsDeque = residentsInCell.get(randomType);
                    int countInCell = residentsDeque.size();
                    if(randomMaxCountInCell==countInCell){
                        continue;
                    }else if(countInCell<randomMaxCountInCell){
                        int vacantPlaces = randomMaxCountInCell-countInCell;
                        int freePlaces = Math.min(randomCount, vacantPlaces);
                        mergeDeque(freePlaces, randomLimit, randomClass, residentsDeque);
                    }
                } else{
                    ArrayDeque<Animal> randomAnimalDeque = fillAnimalDeque(randomCount, randomLimit, randomClass);
                    residentsInCell.put(randomType, randomAnimalDeque);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void mergeDeque(int freePlaces, Limit randomLimit, Class<? extends Animal> randomClass, ArrayDeque<Animal> residentsDeque) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ArrayDeque<Animal> newDeque = residentsDeque;
        TypeOfAnimal randomType = TypeOfAnimal.valueOf(randomClass.getName());
        for (int j = 0; j < freePlaces; j++) {
            Animal randomAnimal = animalCreator.create(randomClass);
            newDeque.add(randomAnimal);
            System.out.println(randomAnimal.getIcon());
        }
        residentsInCell.put(randomType, newDeque);
    }

    private static int defineCountOfTypesOfAnimalInCell(List<TypeOfAnimal> types) {
        int randomCountOfCycles = Randomiser.getRandomCount(0, types.size());
        return randomCountOfCycles;
    }

    private ArrayDeque<Animal> fillAnimalDeque(int randomCount, Limit randomLimit, Class<? extends Animal> randomClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ArrayDeque<Animal> randomAnimalDeque = new ArrayDeque<>();
        for (int j = 0; j < randomCount; j++) {
            Animal randomAnimal = animalCreator.create(randomClass);
            randomAnimalDeque.add(randomAnimal);
            System.out.println(randomAnimal.getIcon());
        }
        return randomAnimalDeque;
    }


}
