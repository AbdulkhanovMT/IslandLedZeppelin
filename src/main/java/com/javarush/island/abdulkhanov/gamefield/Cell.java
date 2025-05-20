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
        for (int i = 0; i < randomCountOfCycles; i++) {
            TypeOfAnimal randomType = types.get(Randomiser.getRandomCount(0, types.size()));
            Class<? extends Animal> randomClass = (Class<? extends Animal>) AnimalMap.ANIMALS.get(randomType);
            int randomMaxCountInCell = 0;
            try {
                Limit randomLimit = randomClass.getConstructor().newInstance().readConfig();
                randomMaxCountInCell = Integer.parseInt(randomLimit.getMaxCountInCell());
                ArrayDeque<Animal> randomAnimalDeque = new ArrayDeque<>();
                fillAnimalDeque(randomMaxCountInCell, randomLimit, randomAnimalDeque, randomClass);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            Set<TypeOfAnimal> keySet = AnimalMap.ANIMALS.keySet();
            Set<Map.Entry<TypeOfAnimal, Class<?>>> entrySet = AnimalMap.ANIMALS.entrySet();
        }
    }

    private static int defineCountOfTypesOfAnimalInCell(List<TypeOfAnimal> types) {
        int randomCountOfCycles = Randomiser.getRandomCount(0, types.size());
        return randomCountOfCycles;
    }

    private static void fillAnimalDeque(int randomMaxCountInCell, Limit randomLimit, ArrayDeque<Animal> randomAnimalDeque, Class<? extends Animal> randomClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (int j = 0; j < randomMaxCountInCell; j++) {
            double maxWeight = Double.parseDouble(randomLimit.getMaxWeight());
            double weight = Randomiser.getRandomWeight(maxWeight/1.5, maxWeight);
            boolean gender = Randomiser.getRandomGender();
            Animal randomAnimal = randomClass
                    .getConstructor(double.class, boolean.class, Limit.class)
                    .newInstance(weight, gender, randomLimit);
            randomAnimalDeque.add(randomAnimal);
            System.out.println(randomAnimal.getIcon());
        }
    }


}
