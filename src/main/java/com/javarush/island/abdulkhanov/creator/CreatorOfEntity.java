package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.util.EntityCounter;
import com.javarush.island.abdulkhanov.util.Randomiser;

import java.lang.reflect.InvocationTargetException;

public class CreatorOfEntity extends Creator<Entity>{
    @Override
    public Entity create(Class<? extends Entity> entityClass) {
        try{
            Limit randomLimit = entityClass.getConstructor().newInstance().readConfig();
            double maxWeight = Double.parseDouble(randomLimit.getMaxWeight());
            double weight = Randomiser.getRandomWeight(maxWeight/1.5, maxWeight);
            boolean gender = Randomiser.getRandomGender();
            Entity randomAnimal = entityClass
                .getConstructor(double.class, boolean.class, Limit.class)
                .newInstance(weight, gender, randomLimit);
            EntityCounter.increasePopulation(getTypeOfEntity(entityClass));
            return randomAnimal;
        }
        catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static TypeOfEntity getTypeOfEntity(Class<? extends Entity> entityClass) {
        return TypeOfEntity.valueOf(entityClass.getSimpleName().toUpperCase());
    }
}
