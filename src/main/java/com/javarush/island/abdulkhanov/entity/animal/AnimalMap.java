package com.javarush.island.abdulkhanov.entity.animal;

import com.javarush.island.abdulkhanov.entity.herbivore.Rabbit;
import com.javarush.island.abdulkhanov.entity.predator.Wolf;

import java.util.HashMap;

public class AnimalMap{
    
    public final static HashMap<TypeOfAnimal, Class<?>> ANIMALS = null;

    static
    {
        ANIMALS.put(TypeOfAnimal.WOLF, Wolf.class);
        ANIMALS.put(TypeOfAnimal.RABBIT, Rabbit.class);
    }


    
}
