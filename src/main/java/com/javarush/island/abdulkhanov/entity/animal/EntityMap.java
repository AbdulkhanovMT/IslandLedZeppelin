package com.javarush.island.abdulkhanov.entity.animal;

import com.javarush.island.abdulkhanov.entity.herbivore.Rabbit;
import com.javarush.island.abdulkhanov.entity.predator.Wolf;

import java.util.concurrent.ConcurrentHashMap;

public class EntityMap {
    
    public final static ConcurrentHashMap<TypeOfEntity, Class<?>> ENTITIES = new ConcurrentHashMap<>();

    static
    {
        ENTITIES.put(TypeOfEntity.WOLF, Wolf.class);
        ENTITIES.put(TypeOfEntity.RABBIT, Rabbit.class);
    }

    public Class<?> get(TypeOfEntity key){
        return ENTITIES.get(key);
    }
}
