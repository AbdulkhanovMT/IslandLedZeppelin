package com.javarush.island.abdulkhanov.creator;

import com.javarush.island.abdulkhanov.entity.plant.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreatorOfPlant extends Creator<Plant> {

    public CreatorOfPlant() {
    }

    public Plant create(Class<? extends Plant> plantClass){
        try {
            Constructor<?> plantConstructor = plantClass.getConstructor();
            return (Plant) plantConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
