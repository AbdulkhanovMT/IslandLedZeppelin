package com.javarush.island.abdulkhanov.entity.predator;

import com.javarush.island.abdulkhanov.entity.Entity;
import com.javarush.island.abdulkhanov.entity.animal.Animal;
import com.javarush.island.abdulkhanov.entity.limit.Limit;
import com.javarush.island.abdulkhanov.gamefield.Cell;

public abstract class Predator extends Animal {
    public Predator() {
    }

    public Predator(double weight, boolean gender, Limit animalLimit) {
        super(weight, gender, animalLimit);
    }

}
