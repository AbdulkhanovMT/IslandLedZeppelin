package com.javarush.island.abdulkhanov.util;

import com.javarush.island.abdulkhanov.entity.animal.TypeOfEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCounter {
    private final static Map<TypeOfEntity, Integer> entitiesRecord = new HashMap<>();

    private EntityCounter() {
    }

    static {
        for (TypeOfEntity value : TypeOfEntity.values()) {
            entitiesRecord.put(value, 0);
        }
    }

    public static Map<TypeOfEntity, Integer> getEntitiesRecord() {
        return entitiesRecord;
    }

    public static void increasePopulation(TypeOfEntity someTypeOfEntity) {
        int record = entitiesRecord.get(someTypeOfEntity);
        entitiesRecord.put(someTypeOfEntity, ++record);
    }

    public static void reducePopulation(TypeOfEntity someTypeOfEntity) {
        int record = entitiesRecord.get(someTypeOfEntity);
        entitiesRecord.put(someTypeOfEntity, --record);
    }

    public static int getSumOfRecords(){
        int sum = 0;
        List<Integer> ints = entitiesRecord.values().stream().toList();
        for (Integer anInt : ints) {
            sum = sum + anInt;
        }
        return sum;
    }

}
