package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.entity.animal.Animal;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreatorOfAnimal extends Creator<Animal>{
    @Override
    public Animal create(Class<? extends Animal> animalClass) throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        Limit randomLimit = animalClass.getConstructor().newInstance().readConfig();
        double maxWeight = Double.parseDouble(randomLimit.getMaxWeight());
        double weight = Randomiser.getRandomWeight(maxWeight/1.5, maxWeight);
        boolean gender = Randomiser.getRandomGender();
        Animal randomAnimal = animalClass
                .getConstructor(double.class, boolean.class, Limit.class)
                .newInstance(weight, gender, randomLimit);
        return randomAnimal;
    }
}
