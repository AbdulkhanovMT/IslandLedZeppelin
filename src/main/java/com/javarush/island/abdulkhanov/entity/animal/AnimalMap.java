package com.javarush.island.abdulkhanov.entity.animal;

import com.javarush.island.abdulkhanov.entity.herbivore.Rabbit;
import com.javarush.island.abdulkhanov.entity.predator.Wolf;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class AnimalMap{
    
    public final static ConcurrentHashMap<TypeOfAnimal, Class<?>> ANIMALS = new ConcurrentHashMap<>();

    static
    {
        ANIMALS.put(TypeOfAnimal.WOLF, Wolf.class);
        ANIMALS.put(TypeOfAnimal.RABBIT, Rabbit.class);
    }

    public Class<?> get(TypeOfAnimal key){
        return ANIMALS.get(key);
    }


    
}
