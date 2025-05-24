package com.javarush.island.abdulkhanov.util;

import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;
import com.javarush.island.abdulkhanov.entity.herbivore.*;
import com.javarush.island.abdulkhanov.entity.plant.Plant;
import com.javarush.island.abdulkhanov.entity.predator.*;

import java.util.concurrent.ConcurrentHashMap;

public class EntityMap {
    
    public final static ConcurrentHashMap<TypeOfEntity, Class<?>> ENTITIES = new ConcurrentHashMap<>();

    private EntityMap(){

    }

    static
    {
        ENTITIES.put(TypeOfEntity.WOLF, Wolf.class);
        ENTITIES.put(TypeOfEntity.FOX, Fox.class);
        ENTITIES.put(TypeOfEntity.BEAR, Bear.class);
        ENTITIES.put(TypeOfEntity.CONSTRICTOR, Constrictor.class);
        ENTITIES.put(TypeOfEntity.EAGLE, Eagle.class);
        ENTITIES.put(TypeOfEntity.HORSE, Horse.class);
        ENTITIES.put(TypeOfEntity.DEER, Deer.class);
        ENTITIES.put(TypeOfEntity.BOAR, Boar.class);
        ENTITIES.put(TypeOfEntity.BUFFALO, Buffalo.class);
        ENTITIES.put(TypeOfEntity.DUCK, Duck.class);
        ENTITIES.put(TypeOfEntity.GOAT, Goat.class);
        ENTITIES.put(TypeOfEntity.SHEEP, Sheep.class);
        ENTITIES.put(TypeOfEntity.MOUSE, Mouse.class);
        ENTITIES.put(TypeOfEntity.CATERPILLAR, Caterpillar.class);
        ENTITIES.put(TypeOfEntity.RABBIT, Rabbit.class);
        ENTITIES.put(TypeOfEntity.PLANT, Plant.class);
    }

}
