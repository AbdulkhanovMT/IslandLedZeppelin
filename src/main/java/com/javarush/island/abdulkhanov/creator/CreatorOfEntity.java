package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.lang.reflect.InvocationTargetException;

public class CreatorOfEntity extends Creator<Entity>{
    @Override
    public Entity create(Class<? extends Entity> animalClass) throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        Limit randomLimit = animalClass.getConstructor().newInstance().readConfig();
        double maxWeight = Double.parseDouble(randomLimit.getMaxWeight());
        double weight = Randomiser.getRandomWeight(maxWeight/1.5, maxWeight);
        boolean gender = Randomiser.getRandomGender();
        Entity randomAnimal = animalClass
                .getConstructor(double.class, boolean.class, Limit.class)
                .newInstance(weight, gender, randomLimit);
        return randomAnimal;
    }
}
