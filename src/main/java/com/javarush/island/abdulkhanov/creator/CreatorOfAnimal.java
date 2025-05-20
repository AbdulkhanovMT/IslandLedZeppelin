package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.entity.animal.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreatorOfAnimal extends Creator<Animal>{
    @Override
    public Animal create(Class<? extends Animal> animalClass) {
        try {
            Constructor<?> animalConstructor = animalClass.getConstructor();
            Animal animal = (Animal) animalConstructor.newInstance();
            animal.readConfig();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
